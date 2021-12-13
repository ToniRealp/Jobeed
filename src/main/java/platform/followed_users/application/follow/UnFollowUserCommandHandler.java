package platform.followed_users.application.follow;

import platform.shared.domain.UserId;
import platform.shared.domain.command.CommandHandler;

public class UnFollowUserCommandHandler implements CommandHandler<UnFollowUserCommand> {
    private final UserFollower follower;

    public UnFollowUserCommandHandler(UserFollower follower) {
        this.follower = follower;
    }

    @Override
    public void handle(UnFollowUserCommand command) throws Exception {
        UserId followerId = new UserId(command.followerId());
        UserId followedId = new UserId(command.followedId());

        follower.unFollow(followerId, followedId);
    }
}
