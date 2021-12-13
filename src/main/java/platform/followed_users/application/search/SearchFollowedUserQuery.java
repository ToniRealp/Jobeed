package platform.followed_users.application.search;

import platform.shared.domain.query.Query;

public class SearchFollowedUserQuery implements Query {
    private final String followerId;
    private final String followedId;

    public SearchFollowedUserQuery(String followerId, String followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public String followerId() {
        return followerId;
    }

    public String followedId() {
        return followedId;
    }
}
