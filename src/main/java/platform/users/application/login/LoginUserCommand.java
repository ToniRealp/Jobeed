package platform.users.application.login;

import platform.shared.domain.command.Command;

public class LoginUserCommand implements Command {
    private final String email;
    private final String password;

    public LoginUserCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }
}
