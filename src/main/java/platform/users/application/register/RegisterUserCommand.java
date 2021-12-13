package platform.users.application.register;

import platform.shared.domain.command.Command;

import java.util.Date;
import java.util.List;

public class RegisterUserCommand implements Command {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final Date birthday;
    private final String gender;
    private final String employment;
    private final List<String> interests;

    public RegisterUserCommand(String id, String name, String email, String password, String confirmPassword, Date birthday, String gender, String employment, List<String> interests) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.birthday = birthday;
        this.gender = gender;
        this.employment = employment;
        this.interests = interests;
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }

    public String confirmPassword() {
        return this.confirmPassword;
    }

    public Date birthday() {
        return this.birthday;
    }

    public String gender() {
        return this.gender;
    }

    public String employment() {
        return this.employment;
    }

    public List<String> interests() {
        return this.interests;
    }
}
