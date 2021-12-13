package platform.followed_users.application.follow;

import platform.shared.domain.command.Command;

public class FollowUserCommand implements Command {

    private final String followerId;
    private final String followedId;

    public FollowUserCommand(String followerId, String followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public String followerId() {
        return followerId;
    }

    public String followedId() {
        return followedId;
    }
}
