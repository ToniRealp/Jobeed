package platform.users.application.update;

import platform.shared.domain.UserId;
import platform.users.domain.*;

public class UserUpdater {
    private final UserRepository userRepository;

    public UserUpdater(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void update(UserId id, UserName name, UserEmail email, UserGender gender, UserEmployment employment) throws Exception {
        userRepository.update(id, name, email, gender, employment);
    }
}
