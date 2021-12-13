package platform.interests;

import platform.interests.domain.Interest;
import platform.shared.domain.query.QueryResult;

import java.util.List;

public class InterestsQueryResult implements QueryResult {
    private final List<Interest> interests;

    public InterestsQueryResult(List<Interest> interests) {
        this.interests = interests;
    }

    public static InterestsQueryResult from(List<Interest> interests) {
        return new InterestsQueryResult(interests);
    }

    public List<Interest> getInterests() {
        return interests;
    }
}
