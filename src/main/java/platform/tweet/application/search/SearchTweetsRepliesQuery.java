package platform.tweet.application.search;

import platform.shared.domain.query.Query;

public class SearchTweetsRepliesQuery implements Query {
    private final String tweetId;

    public SearchTweetsRepliesQuery(String tweetId) {
        this.tweetId = tweetId;
    }

    public String tweetId() {
        return tweetId;
    }
}
