package platform.users.application.register;

import platform.interests.application.InterestSearcher;
import platform.interests.domain.Interest;
import platform.interests.domain.InterestId;
import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryBus;
import platform.user_interests.application.create.UserInterestCreator;
import platform.user_interests.domain.UserInterest;
import platform.user_pictures.application.UserPictureQueryResult;
import platform.user_pictures.application.find.FindUserPictureQuery;
import platform.users.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserRegister {

    private final InterestSearcher interestSearcher;
    private final UserRepository userRepository;
    private final UserInterestCreator userInterestCreator;
    private final QueryBus queryBus;

    public UserRegister(InterestSearcher interestSearcher, UserRepository userRepository, UserInterestCreator userInterestCreator, QueryBus queryBus) {
        this.interestSearcher = interestSearcher;
        this.userRepository = userRepository;
        this.userInterestCreator = userInterestCreator;
        this.queryBus = queryBus;
    }

    public void register(UserId id, UserName name, UserEmail email, UserPassword password, UserPassword confirmPassword, Date birthday, UserGender gender, UserEmployment employment, List<InterestId> interestIds) throws Exception {
        ensureUserEmailDoesNotExist(email);
        ensurePasswordMatch(password, confirmPassword);

        List<Interest> interests = interestSearcher.searchAllIn(interestIds);
        List<UserInterest> userInterests = interests.stream().map(interest -> new UserInterest(id, interest.id(), interest.name())).collect(Collectors.toList());

        UserPictureQueryResult pictureQuery = queryBus.ask(new FindUserPictureQuery(email.value()));
        UserPicture picture = new UserPicture(pictureQuery.picture());

        User user = new User(id, name, email, password, picture, birthday, gender, employment, UserRole.REGISTERED);

        userRepository.save(user);
        userInterestCreator.create(userInterests);
    }

    private void ensurePasswordMatch(UserPassword password, UserPassword confirmPassword) throws PasswordsDoNotMatch {
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatch();
        }
    }

    private void ensureUserEmailDoesNotExist(UserEmail email) throws UserEmailAlreadyExists {
        Optional<User> user = userRepository.searchByEmail(email);
        if (user.isPresent()) {
            throw new UserEmailAlreadyExists();
        }
    }

}
