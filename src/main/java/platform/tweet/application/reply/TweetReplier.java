package platform.tweet.application.reply;

import platform.tweet.domain.TweetId;
import platform.tweet.domain.TweetRepository;

public class TweetReplier {
    private final TweetRepository repository;

    public TweetReplier(TweetRepository repository) {
        this.repository = repository;
    }

    public void incrementReplyCount(TweetId parentTweetId) throws Exception {
        repository.incrementReplyCount(parentTweetId);
    }

    public void decrementReplyCount(TweetId parentTweetId) throws Exception {
        repository.decrementReplyCount(parentTweetId);
    }
}
