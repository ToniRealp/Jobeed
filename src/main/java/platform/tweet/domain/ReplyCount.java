package platform.tweet.domain;

import platform.shared.domain.IntValueObject;

public class ReplyCount extends IntValueObject {
    public ReplyCount(int count) {
        if (count < 0) {
            throw new ReplyCountBelowZero();
        }
        value = count;
    }
}
