package platform.users.application.update;

import platform.shared.domain.command.Command;

public class UpdateUserCommand implements Command {

    private String id;
    private String name;
    private String email;
    private String employment;
    private String gender;

    public UpdateUserCommand(String id, String name, String email, String employment, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.employment = employment;
        this.gender = gender;
    }
    public String id() { return id; }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String employment() {
        return employment;
    }

    public String gender() {
        return gender;
    }
}
