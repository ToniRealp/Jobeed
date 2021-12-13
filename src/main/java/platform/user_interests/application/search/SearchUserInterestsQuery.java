package platform.user_interests.application.search;

import platform.shared.domain.query.Query;

public class SearchUserInterestsQuery implements Query {
    private final String userId;

    public SearchUserInterestsQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
