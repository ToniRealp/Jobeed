package platform.tweet.application.create;

import platform.shared.domain.command.CommandHandler;
import platform.tweet.domain.TweetContent;
import platform.tweet.domain.TweetId;
import platform.shared.domain.UserId;

public class CreateTweetCommandHandler implements CommandHandler<CreateTweetCommand> {
    private final TweetCreator creator;

    public CreateTweetCommandHandler(TweetCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateTweetCommand command) throws Exception {
        TweetId id = new TweetId(command.id());
        TweetContent content = new TweetContent(command.content());
        UserId authorId = new UserId(command.authorId());
        TweetId parentTweetId = new TweetId(command.parentTweetId());

        creator.create(id, content, authorId, parentTweetId);
    }
}
