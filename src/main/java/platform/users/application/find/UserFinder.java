package platform.users.application.find;

import platform.users.domain.User;
import platform.users.domain.UserEmail;
import platform.shared.domain.UserId;
import platform.users.domain.UserRepository;

public class UserFinder {

    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(UserEmail email) throws Exception {
        return userRepository.findByEmail(email);
    }

    public User findById(UserId id) throws Exception {
        return userRepository.findById(id);
    }

}
