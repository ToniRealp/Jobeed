package platform.tweet.domain;

import platform.shared.domain.StringValueObject;

public class TweetContent extends StringValueObject {
    private static final int MAX_TWEET_LENGTH = 140;

    public TweetContent(String text) {
        super(text);
        if (text.length() > MAX_TWEET_LENGTH) {
            throw new TweetContentTooLong();
        }
    }
}
