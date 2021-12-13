package platform.tweet.domain;

import platform.shared.domain.ValueObjectException;

public class ReplyCountBelowZero extends ValueObjectException {
    public ReplyCountBelowZero() {
        super("Tweet reply count cannot be below 0");
    }
}
