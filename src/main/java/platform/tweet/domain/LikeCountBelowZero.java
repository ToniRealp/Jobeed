package platform.tweet.domain;

import platform.shared.domain.ValueObjectException;

public class LikeCountBelowZero extends ValueObjectException {
    public LikeCountBelowZero() {
        super("Tweet like count cannot be below 0");
    }
}
