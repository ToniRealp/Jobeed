package platform.users.application.search;

import platform.shared.domain.query.Query;

public class SearchUsersWithSimilarInterestsQuery implements Query {
    private final String userId;

    public SearchUsersWithSimilarInterestsQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
