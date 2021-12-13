package platform.interests.domain;

import platform.shared.domain.InvalidUUID;
import platform.shared.domain.UUID;

public class InterestId extends UUID {
    public InterestId(String id) throws InvalidUUID {
        super(id);
    }
}
