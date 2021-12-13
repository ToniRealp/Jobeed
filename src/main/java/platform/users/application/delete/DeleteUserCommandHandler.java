package platform.users.application.delete;

import platform.shared.domain.UserId;
import platform.shared.domain.command.CommandHandler;

public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand> {
    private final UserDeleter deleter;


    public DeleteUserCommandHandler(UserDeleter deleter) {
        this.deleter = deleter;
    }

    @Override
    public void handle(DeleteUserCommand command) throws Exception {
        UserId id = new UserId(command.id());
        deleter.delete(id);
    }
}
