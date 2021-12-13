package platform.tweet.application.create;

import platform.shared.domain.query.QueryBus;
import platform.tweet.application.reply.TweetReplier;
import platform.tweet.domain.*;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;
import platform.users.domain.UserEmail;
import platform.shared.domain.UserId;
import platform.users.domain.UserName;
import platform.users.domain.UserPicture;

import java.time.Instant;

public class TweetCreator {
    private final TweetRepository repository;
    private final QueryBus queryBus;
    private final TweetReplier replies;

    public TweetCreator(TweetRepository repository, QueryBus queryBus, TweetReplier replies) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.replies = replies;
    }

    public void create(TweetId id, TweetContent content, UserId authorId, TweetId parentTweetId) throws Exception {
        UserQueryResult user = queryBus.ask(new FindUserByIdQuery(authorId.value()));

        LikeCount likeCount = new LikeCount(0);
        ReplyCount replyCount = new ReplyCount(0);
        UserName authorName = new UserName(user.name());
        UserEmail authorEmail = new UserEmail(user.email());
        UserPicture authorPicture = new UserPicture(user.picture());

        Tweet tweet = new Tweet(id, content, likeCount, replyCount, authorId, authorName, authorEmail, authorPicture, parentTweetId, Instant.now());
        repository.save(tweet);

        if (parentTweetId.value() != null) {
            replies.incrementReplyCount(parentTweetId);
        }
    }
}
