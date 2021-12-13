package platform.tweet.application.search;

import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryHandler;
import platform.tweet.application.TweetsQueryResult;

public class SearchUserTweetsQueryHandler implements QueryHandler<SearchUserTweetsQuery, TweetsQueryResult> {
    private final TweetSearcher searcher;

    public SearchUserTweetsQueryHandler(TweetSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public TweetsQueryResult handle(SearchUserTweetsQuery query) throws Exception {
        UserId authorId = new UserId(query.userId());
        return TweetsQueryResult.from(searcher.searchByAuthor(authorId));
    }
}
