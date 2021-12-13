package platform.tweet.infrastructure;

import platform.shared.domain.criteria.*;
import platform.shared.infrastructure.MySqlConnection;
import platform.shared.infrastructure.MySqlCriteriaAdapter;
import platform.tweet.domain.*;
import platform.users.domain.*;
import platform.shared.domain.UserId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MySqlTweetRepository implements TweetRepository {
    private final MySqlConnection connection;
    private final MySqlCriteriaAdapter criteriaAdapter;

    public MySqlTweetRepository(MySqlConnection connection, MySqlCriteriaAdapter criteriaAdapter) {
        this.connection = connection;
        this.criteriaAdapter = criteriaAdapter;
    }

    @Override
    public void save(Tweet tweet) throws Exception {
        try {
            if (exists(tweet.id())) {
                update(tweet);
            } else {
                insert(tweet);
            }
        } catch (Exception exception) {
            throw new Exception("MySQL failed");
        }
    }

    private void insert(Tweet tweet) throws SQLException {
        String query = "INSERT INTO tweets (id,content,likeCount,replyCount,authorId,authorName,parentTweetId,createdOn,authorEmail,authorPicture) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, tweet.id().value());
        statement.setString(2, tweet.content().value());
        statement.setInt(3, tweet.likeCount().value());
        statement.setInt(4, tweet.replyCount().value());
        statement.setString(5, tweet.authorId().value());
        statement.setString(6, tweet.authorName().value());
        statement.setString(7, tweet.parentTweetId().value());
        statement.setTimestamp(8, Timestamp.from(tweet.createdOn()));
        statement.setString(9, tweet.authorEmail().value());
        statement.setString(10, tweet.authorPicture().value());
        statement.executeUpdate();
        statement.close();
    }

    private void update(Tweet tweet) throws SQLException {
        String query = "UPDATE tweets SET content = ?, likeCount = ?, replyCount = ?, authorId = ?, authorName = ?, parentTweetId = ?, createdOn = ?, authorEmail = ?, authorPicture = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, tweet.content().value());
        statement.setInt(2, tweet.likeCount().value());
        statement.setInt(3, tweet.replyCount().value());
        statement.setString(4, tweet.authorId().value());
        statement.setString(5, tweet.authorName().value());
        statement.setString(6, tweet.parentTweetId().value());
        statement.setTimestamp(7, Timestamp.from(tweet.createdOn()));
        statement.setString(8, tweet.authorEmail().value());
        statement.setString(9, tweet.authorPicture().value());
        statement.setString(10, tweet.id().value());
        statement.executeUpdate();
        statement.close();
    }

    public void incrementLikeCount(TweetId tweetId) throws SQLException{
        String query = "UPDATE tweets SET likeCount = likeCount + 1 WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, tweetId.value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void incrementReplyCount(TweetId tweetId) throws Exception {
        String query = "UPDATE tweets SET replyCount = replyCount + 1 WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, tweetId.value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void decrementReplyCount(TweetId tweetId) throws Exception {
        String query = "UPDATE tweets SET replyCount = replyCount - 1 WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, tweetId.value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public List<Tweet> matching(Criteria criteria) {
        try {
            String query = criteriaAdapter.adapt(criteria, "tweets");
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            List<Tweet> tweets = new ArrayList<>();
            while (resultSet.next()) {
                tweets.add(deserializeTweet(resultSet));
            }
            statement.close();
            return tweets;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteById(TweetId id) throws Exception {
        String query = "DELETE FROM tweets WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id.value());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Tweet findById(TweetId id) throws Exception {
        String query = "SELECT * FROM tweets WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id.value());

        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            throw new TweetNotFound();
        }

        Tweet tweet = deserializeTweet(resultSet);
        statement.close();
        return tweet;
    }

    @Override
    public boolean exists(TweetId id) {
        Criteria criteria = new Criteria(new Filters(Filter.by("id", FilterOperator.EQUAL, id.value())), Orders.none(), 1, 0);
        List<Tweet> tweets = matching(criteria);
        return tweets.size() > 0;
    }

    private Tweet deserializeTweet(ResultSet result) throws SQLException {
        TweetId id = new TweetId(result.getString("id"));
        TweetContent content = new TweetContent(result.getString("content"));
        LikeCount likeCount = new LikeCount(result.getInt("likeCount"));
        ReplyCount replyCount = new ReplyCount(result.getInt("replyCount"));
        UserId authorId = new UserId(result.getString("authorId"));
        UserName authorName = new UserName(result.getString("authorName"));
        UserEmail authorEmail = new UserEmail(result.getString("authorEmail"));
        UserPicture authorPicture = new UserPicture(result.getString("authorPicture"));
        TweetId parentTweetId = new TweetId(result.getString("parentTweetId"));
        Instant createdOn = result.getTimestamp("createdOn").toInstant();
        return new Tweet(id, content, likeCount, replyCount, authorId, authorName, authorEmail, authorPicture, parentTweetId, createdOn);
    }

}
