package platform.tweet.application;

import platform.shared.domain.query.QueryResult;
import platform.tweet.domain.Tweet;

import java.util.List;
import java.util.stream.Collectors;

public class TweetsQueryResult implements QueryResult {
    private final List<TweetQueryResult> tweets;

    public TweetsQueryResult(List<TweetQueryResult> tweets) {
        this.tweets = tweets;
    }

    public static TweetsQueryResult from(List<Tweet> tweets) {
        return new TweetsQueryResult(tweets.stream().map(TweetQueryResult::from).collect(Collectors.toList()));
    }

    public List<TweetQueryResult> tweets() {
        return tweets;
    }
}
