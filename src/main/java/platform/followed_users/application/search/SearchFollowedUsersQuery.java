package platform.followed_users.application.search;

import platform.shared.domain.query.Query;

public class SearchFollowedUsersQuery implements Query {
    private final String userId;

    public SearchFollowedUsersQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
