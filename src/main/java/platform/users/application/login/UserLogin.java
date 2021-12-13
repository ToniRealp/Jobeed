package platform.users.application.login;

import platform.users.domain.*;


public class UserLogin {

    private final UserRepository userRepository;

    public UserLogin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(UserEmail email, UserPassword password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (!user.password().equals(password)) {
            throw new InvalidCredentials();
        }
    }

}
