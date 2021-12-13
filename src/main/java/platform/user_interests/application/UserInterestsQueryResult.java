package platform.user_interests.application;

import platform.shared.domain.query.QueryResult;
import platform.user_interests.domain.UserInterest;

import java.util.List;
import java.util.stream.Collectors;

public class UserInterestsQueryResult implements QueryResult {
    private final List<UserInterestQueryResult> userInterest;

    public UserInterestsQueryResult(List<UserInterestQueryResult> userInterests) {
        this.userInterest = userInterests;
    }

    public static UserInterestsQueryResult from(List<UserInterest> userInterests) {
        return new UserInterestsQueryResult(userInterests.stream().map(UserInterestQueryResult::from).collect(Collectors.toList()));
    }

    public List<UserInterestQueryResult> userInterest() {
        return userInterest;
    }
}
