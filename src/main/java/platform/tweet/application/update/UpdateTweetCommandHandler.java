package platform.tweet.application.update;

import platform.shared.domain.UserId;
import platform.shared.domain.command.CommandHandler;
import platform.tweet.domain.TweetContent;
import platform.tweet.domain.TweetId;

public class UpdateTweetCommandHandler implements CommandHandler<UpdateTweetCommand> {
    private final TweetUpdater updater;

    public UpdateTweetCommandHandler(TweetUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void handle(UpdateTweetCommand command) throws Exception {
        TweetId id = new TweetId(command.id());
        TweetContent content = new TweetContent(command.content());
        UserId authorId = new UserId(command.authorId());
        updater.update(id, content, authorId);
    }
}
