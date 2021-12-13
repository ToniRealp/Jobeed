package platform.tweet.application.search;

import platform.shared.domain.query.QueryHandler;
import platform.tweet.application.TweetsQueryResult;
import platform.tweet.domain.Tweet;
import platform.shared.domain.UserId;

import java.util.List;

public class SearchTrendingTweetsOfContactsQueryHandler implements QueryHandler<SearchTrendingTweetsOfContactsQuery, TweetsQueryResult> {
    private final TweetSearcher tweetSearcher;

    public SearchTrendingTweetsOfContactsQueryHandler(TweetSearcher searcher) {
        this.tweetSearcher = searcher;
    }

    @Override
    public TweetsQueryResult handle(SearchTrendingTweetsOfContactsQuery query) throws Exception {
        UserId userId = new UserId(query.userId());
        List<Tweet> tweets = tweetSearcher.searchTrendingTweetsOfContacts(userId);
        return TweetsQueryResult.from(tweets);
    }
}
