package platform.user_interests.application;

import platform.shared.domain.query.QueryResult;
import platform.user_interests.domain.UserInterest;

public class UserInterestQueryResult implements QueryResult {
    private final String userId;
    private final String interestId;
    private final String interestName;

    public UserInterestQueryResult(String userId, String interestId, String interestName) {
        this.userId = userId;
        this.interestId = interestId;
        this.interestName = interestName;
    }

    public static UserInterestQueryResult from(UserInterest userInterest) {
        return new UserInterestQueryResult(userInterest.userId().value(), userInterest.interestId().value(), userInterest.interestName());
    }

    public String userId() {
        return userId;
    }

    public String interestId() {
        return interestId;
    }

    public String interestName() {
        return interestName;
    }

}
