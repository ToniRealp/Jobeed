package platform.tweet.application.find;

import platform.shared.domain.query.Query;

public class FindTweetByIdQuery implements Query {
    private final String tweetId;

    public String tweetId() {
        return tweetId;
    }

    public FindTweetByIdQuery(String tweetId) {
        this.tweetId = tweetId;
    }
}
