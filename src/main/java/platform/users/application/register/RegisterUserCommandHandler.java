package platform.users.application.register;

import platform.interests.domain.InterestId;
import platform.shared.domain.UserId;
import platform.shared.domain.command.CommandHandler;
import platform.users.domain.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterUserCommandHandler implements CommandHandler<RegisterUserCommand> {
    private final UserRegister register;

    public RegisterUserCommandHandler(UserRegister register) {
        this.register = register;
    }

    @Override
    public void handle(RegisterUserCommand command) throws Exception {
        UserId id = new UserId(command.id());
        UserName name = new UserName(command.name());
        UserEmail email = new UserEmail(command.email());
        UserPassword password = new UserPassword(command.password());
        UserPassword confirmPassword = new UserPassword(command.confirmPassword());
        Date birthday = command.birthday();
        UserGender gender = UserGender.valueOf(command.gender());
        UserEmployment employment = UserEmployment.valueOf(command.employment());
        List<InterestId> interestIds = command.interests().stream().map(InterestId::new).collect(Collectors.toList());

        register.register(id, name, email, password, confirmPassword, birthday, gender, employment, interestIds);
    }
}
