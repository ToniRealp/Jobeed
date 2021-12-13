package platform.tweet.application.find;

import platform.shared.domain.query.QueryHandler;
import platform.tweet.application.TweetQueryResult;
import platform.tweet.domain.TweetId;

public class FindTweetByIdQueryHandler implements QueryHandler<FindTweetByIdQuery, TweetQueryResult> {
    private final TweetFinder finder;

    public FindTweetByIdQueryHandler(TweetFinder finder) {
        this.finder = finder;
    }

    @Override
    public TweetQueryResult handle(FindTweetByIdQuery query) throws Exception {
        return TweetQueryResult.from(finder.findById(new TweetId(query.tweetId())));
    }
}
