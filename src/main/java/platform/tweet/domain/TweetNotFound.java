package platform.tweet.domain;

import platform.shared.domain.ValueObjectException;

public class TweetNotFound extends ValueObjectException {
    public TweetNotFound() {
        super("Tweet not found");
    }
}
