package platform.tweet.application;

import platform.shared.domain.query.QueryResult;
import platform.tweet.domain.Tweet;

public class TweetQueryResult implements QueryResult {
    private final String id;
    private final String content;
    private final Integer likeCount;
    private final Integer replyCount;
    private final String authorId;
    private final String authorName;
    private final String authorEmail;
    private final String authorPicture;
    private final String parentTweetId;
    private final String createdOn;

    public TweetQueryResult(String id, String content, Integer likeCount, Integer replyCount, String authorId, String authorName, String authorEmail, String authorPicture, String parentTweetId, String createdOn) {
        this.id = id;
        this.content = content;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorPicture = authorPicture;
        this.parentTweetId = parentTweetId;
        this.createdOn = createdOn;
    }

    public static TweetQueryResult from(Tweet tweet) {
        return new TweetQueryResult(
                tweet.id().value(),
                tweet.content().value(),
                tweet.likeCount().value(),
                tweet.replyCount().value(),
                tweet.authorId().value(),
                tweet.authorName().value(),
                tweet.authorEmail().value(),
                tweet.authorPicture().value(),
                tweet.parentTweetId().value(),
                tweet.createdOn().toString()
        );
    }

    public String id() {
        return id;
    }

    public String content() {
        return content;
    }

    public Integer likeCount() {
        return likeCount;
    }

    public Integer replyCount() {
        return replyCount;
    }

    public String authorId() {
        return authorId;
    }

    public String authorName() {
        return authorName;
    }

    public String parentTweetId() {
        return parentTweetId;
    }

    public String createdOn() {
        return createdOn;
    }

    public String authorPicture() {
        return authorPicture;
    }

    public String authorEmail() {
        return authorEmail;
    }
}
