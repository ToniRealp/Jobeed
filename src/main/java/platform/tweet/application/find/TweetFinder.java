package platform.tweet.application.find;

import platform.tweet.domain.Tweet;
import platform.tweet.domain.TweetId;
import platform.tweet.domain.TweetRepository;

public class TweetFinder {
    private final TweetRepository repository;

    public TweetFinder(TweetRepository repository) {
        this.repository = repository;
    }

    public Tweet findById(TweetId id) throws Exception {
        return repository.findById(id);
    }
}
