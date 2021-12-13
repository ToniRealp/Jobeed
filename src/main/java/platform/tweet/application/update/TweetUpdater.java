package platform.tweet.application.update;

import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryBus;
import platform.tweet.domain.Tweet;
import platform.tweet.domain.TweetContent;
import platform.tweet.domain.TweetId;
import platform.tweet.domain.TweetRepository;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;
import platform.users.domain.UserRole;

public class TweetUpdater {
    private final TweetRepository repository;
    private final QueryBus queryBus;

    public TweetUpdater(TweetRepository repository, QueryBus queryBus) {
        this.repository = repository;
        this.queryBus = queryBus;
    }

    public void update(TweetId id, TweetContent content, UserId authorId) throws Exception {
        Tweet tweet = repository.findById(id);
        if (canUpdateTweet(tweet, authorId)) {
            tweet.updateContent(content);
            repository.save(tweet);
        }
    }

    private boolean canUpdateTweet(Tweet tweet, UserId authorId) throws Exception {
        UserQueryResult user = queryBus.ask(new FindUserByIdQuery(authorId.value()));
        Boolean isAdmin = user.role().equals(UserRole.ADMIN.value());
        Boolean isOwner = tweet.authorId().value().equals(authorId.value());
        return isAdmin || isOwner;
    }
}
