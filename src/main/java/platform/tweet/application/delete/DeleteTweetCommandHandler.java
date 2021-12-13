package platform.tweet.application.delete;

import platform.shared.domain.UserId;
import platform.shared.domain.command.CommandHandler;
import platform.tweet.domain.TweetId;

public class DeleteTweetCommandHandler implements CommandHandler<DeleteTweetCommand> {
    private final TweetDeleter deleter;

    public DeleteTweetCommandHandler(TweetDeleter deleter) {
        this.deleter = deleter;
    }

    @Override
    public void handle(DeleteTweetCommand command) throws Exception {
        deleter.deleteById(new TweetId(command.id()), new UserId(command.authorId()));
    }
}
