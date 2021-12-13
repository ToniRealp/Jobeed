package platform.users.application.update;

import platform.shared.domain.UserId;
import platform.shared.domain.command.CommandHandler;
import platform.users.domain.*;

public class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand> {
    private final UserUpdater updater;

    public UpdateUserCommandHandler(UserUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void handle(UpdateUserCommand command) throws Exception {
        UserId id = new UserId(command.id());
        UserName name = new UserName(command.name());
        UserEmail email = new UserEmail(command.email());
        UserGender gender = UserGender.valueOf(command.gender());
        UserEmployment employment = UserEmployment.valueOf(command.employment());

        updater.update(id, name, email, gender, employment);
    }
}
