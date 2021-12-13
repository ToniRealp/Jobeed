package platform.tweet.application.create;

import platform.shared.domain.command.Command;


public class CreateTweetCommand implements Command {
    private final String id;
    private final String content;
    private final String authorId;
    private final String parentTweetId;

    public CreateTweetCommand(String id, String content, String authorId, String parentTweetId) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.parentTweetId = parentTweetId;
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

    public String parentTweetId() {
        return parentTweetId;
    }
}
