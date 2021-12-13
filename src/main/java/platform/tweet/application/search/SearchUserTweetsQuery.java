package platform.tweet.application.search;

import platform.shared.domain.query.Query;

public class SearchUserTweetsQuery implements Query {
    private final String userId;

    public SearchUserTweetsQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
