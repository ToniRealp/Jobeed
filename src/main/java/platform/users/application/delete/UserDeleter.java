package platform.users.application.delete;

import platform.shared.domain.UserId;
import platform.users.domain.UserRepository;

public class UserDeleter {

    private final UserRepository userRepository;

    public UserDeleter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(UserId id) throws Exception {
        userRepository.delete(id);
    }
}
