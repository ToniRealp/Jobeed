package platform.tweet.domain;

import platform.shared.domain.InvalidUUID;
import platform.shared.domain.UUID;

public class TweetId extends UUID {
    public TweetId(String id) throws InvalidUUID {
        super(id);
    }
}
