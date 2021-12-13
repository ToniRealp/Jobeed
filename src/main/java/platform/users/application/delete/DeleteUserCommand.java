package platform.users.application.delete;

import platform.shared.domain.command.Command;

public class DeleteUserCommand implements Command {
    private String id;

    public DeleteUserCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
