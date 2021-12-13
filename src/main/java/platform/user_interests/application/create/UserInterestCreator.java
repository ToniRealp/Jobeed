package platform.user_interests.application.create;

import platform.user_interests.domain.UserInterest;
import platform.user_interests.domain.UserInterestRepository;

import java.util.List;

public class UserInterestCreator {

    private final UserInterestRepository userInterestRepository;

    public UserInterestCreator(UserInterestRepository userInterestRepository) {
        this.userInterestRepository = userInterestRepository;
    }

    public void create(List<UserInterest> userInterests) throws Exception {
        userInterestRepository.saveAll(userInterests);
    }

}
