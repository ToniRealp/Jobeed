package platform.tweet.application.search;

import platform.shared.domain.query.QueryHandler;
import platform.tweet.application.TweetsQueryResult;
import platform.tweet.domain.Tweet;

import java.util.List;

public class SearchTrendingTweetsQueryHandler implements QueryHandler<SearchTrendingTweetsQuery, TweetsQueryResult> {
    private final TweetSearcher tweetSearcher;

    public SearchTrendingTweetsQueryHandler(TweetSearcher searcher) {
        this.tweetSearcher = searcher;
    }

    @Override
    public TweetsQueryResult handle(SearchTrendingTweetsQuery query) throws Exception {
        List<Tweet> tweets = tweetSearcher.searchLastPopularTweets();
        return TweetsQueryResult.from(tweets);
    }
}
