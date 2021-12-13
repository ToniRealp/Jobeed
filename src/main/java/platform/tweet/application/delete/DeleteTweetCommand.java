package platform.tweet.application.delete;

import platform.shared.domain.command.Command;

public class DeleteTweetCommand implements Command {
    private final String id;
    private final String authorId;

    public DeleteTweetCommand(String id, String authorId) {
        this.id = id;
        this.authorId = authorId;
    }

    public String id() {
        return id;
    }

    public String authorId() {
        return authorId;
    }
}
