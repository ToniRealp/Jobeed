package platform.tweet.application.like;

import platform.shared.domain.command.Command;

public class LikeTweetCommand implements Command {
    private String id;

    public LikeTweetCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
