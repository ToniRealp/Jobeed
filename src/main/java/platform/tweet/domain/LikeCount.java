package platform.tweet.domain;

import platform.shared.domain.IntValueObject;

public class LikeCount extends IntValueObject {
    public LikeCount(int count) {
        if (count < 0) {
            throw new LikeCountBelowZero();
        }
        value = count;
    }
}
