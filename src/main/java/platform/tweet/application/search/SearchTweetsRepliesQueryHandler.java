package platform.tweet.application.search;

import platform.shared.domain.query.QueryHandler;
import platform.tweet.application.TweetsQueryResult;
import platform.tweet.domain.TweetId;

public class SearchTweetsRepliesQueryHandler implements QueryHandler<SearchTweetsRepliesQuery, TweetsQueryResult> {
    private final TweetSearcher searcher;

    public SearchTweetsRepliesQueryHandler(TweetSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public TweetsQueryResult handle(SearchTweetsRepliesQuery query) throws Exception {
        return TweetsQueryResult.from(searcher.searchReplies(new TweetId(query.tweetId())));
    }
}
