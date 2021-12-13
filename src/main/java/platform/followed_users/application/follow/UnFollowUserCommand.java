package platform.followed_users.application.follow;

import platform.shared.domain.command.Command;

public class UnFollowUserCommand implements Command {

    private final String followerId;
    private final String followedId;

    public UnFollowUserCommand(String followerId, String followedId) {
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
