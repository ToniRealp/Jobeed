package platform.user_interests.domain;

import platform.interests.domain.InterestId;
import platform.shared.domain.UserId;

public class UserInterest {
    private final UserId userId;
    private final InterestId interestId;
    private final String interestName;

    public UserInterest(UserId userId, InterestId interestId, String interestName) {
        this.userId = userId;
        this.interestId = interestId;
        this.interestName = interestName;
    }

    public UserId userId() {
        return this.userId;
    }

    public InterestId interestId() {
        return this.interestId;
    }

    public String interestName() {
        return interestName;
    }
}
