package platform.tweet.application.delete;

import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryBus;
import platform.tweet.application.find.TweetFinder;
import platform.tweet.application.reply.TweetReplier;
import platform.tweet.domain.Tweet;
import platform.tweet.domain.TweetId;
import platform.tweet.domain.TweetRepository;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;
import platform.users.domain.UserRole;

public class TweetDeleter {
    private final TweetRepository repository;
    private final TweetFinder finder;
    private final QueryBus queryBus;
    private final TweetReplier replier;

    public TweetDeleter(TweetRepository repository, TweetFinder finder, QueryBus queryBus, TweetReplier replier) {
        this.repository = repository;
        this.finder = finder;
        this.queryBus = queryBus;
        this.replier = replier;
    }

    public void deleteById(TweetId id, UserId userId) throws Exception {
        Tweet tweet = finder.findById(id);

        if (hasPermissionsToDelete(tweet, userId)) {
            repository.deleteById(id);
            if (tweet.parentTweetId().value() != null) {
                replier.decrementReplyCount(tweet.parentTweetId());
            }
        }
    }

    private boolean hasPermissionsToDelete(Tweet tweet, UserId userId) throws Exception {
        UserQueryResult user = queryBus.ask(new FindUserByIdQuery(userId.value()));
        Boolean isAuthor = tweet.authorId().equals(userId);
        Boolean isAdmin = user.role().equals(UserRole.ADMIN.value());
        return isAuthor || isAdmin;
    }

}
