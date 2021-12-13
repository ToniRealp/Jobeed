package platform.tweet.domain;

import platform.shared.domain.ValueObjectException;

public class TweetContentTooLong extends ValueObjectException {
    public TweetContentTooLong() {
        super("Tweet content exceeds maximum number of characters");
    }
}
