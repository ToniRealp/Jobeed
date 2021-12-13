package platform.tweet.application.update;

import platform.shared.domain.command.Command;

public class UpdateTweetCommand implements Command {
    private final String id;
    private final String content;
    private final String authorId;

    public UpdateTweetCommand(String id, String content, String authorId) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
    }

    public String id() {
        return id;
    }

    public String content() {
        return content;
    }

    public String authorId() {
        return authorId;
    }
}
