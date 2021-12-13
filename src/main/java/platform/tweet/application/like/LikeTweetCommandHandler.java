package platform.tweet.application.like;

import platform.shared.domain.command.CommandHandler;
import platform.tweet.domain.TweetId;

public class LikeTweetCommandHandler implements CommandHandler<LikeTweetCommand> {
    private final TweetLiker liker;

    public LikeTweetCommandHandler(TweetLiker liker) {
        this.liker = liker;
    }

    @Override
    public void handle(LikeTweetCommand command) throws Exception {
        TweetId tweetId = new TweetId(command.id());
        liker.like(tweetId);
    }
}
