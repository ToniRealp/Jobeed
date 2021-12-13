package platform.tweet.domain;

import platform.shared.domain.criteria.Criteria;

import java.util.List;

public interface TweetRepository {
    void save(Tweet tweet) throws Exception;
    List<Tweet> matching(Criteria criteria);
    void deleteById(TweetId id) throws Exception;
    Tweet findById(TweetId id) throws Exception;
    boolean exists(TweetId id) throws Exception;
    void incrementLikeCount(TweetId tweetId) throws Exception;
    void incrementReplyCount(TweetId tweetId) throws Exception;
    void decrementReplyCount(TweetId tweetId) throws Exception;
}
