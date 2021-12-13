package platform.tweet.application.like;

import platform.tweet.domain.TweetId;
import platform.tweet.domain.TweetRepository;

public class TweetLiker {
    private final TweetRepository tweetRepository;

    public TweetLiker(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public void like(TweetId tweetId) throws Exception {
        tweetRepository.incrementLikeCount(tweetId);
    }
}
