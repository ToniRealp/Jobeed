package platform.tweet.application.search;

import platform.shared.domain.query.Query;

public class SearchTrendingTweetsOfContactsQuery implements Query {
    private final String userId;

    public SearchTrendingTweetsOfContactsQuery(String userId) {
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
