package platform.users.application.login;

import platform.shared.domain.command.CommandHandler;
import platform.users.domain.*;

public class LoginUserCommandHandler implements CommandHandler<LoginUserCommand> {
    private final UserLogin login;

    public LoginUserCommandHandler(UserLogin login) {
        this.login = login;
    }

    @Override
    public void handle(LoginUserCommand command) throws Exception {
        UserEmail email = new UserEmail(command.email());
        UserPassword password = new UserPassword(command.password());

        login.login(email, password);
    }
}
