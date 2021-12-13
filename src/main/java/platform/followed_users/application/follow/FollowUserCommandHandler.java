package platform.followed_users.application.follow;

import platform.shared.domain.command.CommandHandler;
import platform.shared.domain.UserId;

public class FollowUserCommandHandler implements CommandHandler<FollowUserCommand> {
    private final UserFollower follower;

    public FollowUserCommandHandler(UserFollower follower) {
        this.follower = follower;
    }

    @Override
    public void handle(FollowUserCommand command) throws Exception {
        UserId followerId = new UserId(command.followerId());
        UserId followedId = new UserId(command.followedId());

        follower.follow(followerId, followedId);
    }
}
