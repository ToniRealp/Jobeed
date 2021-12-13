package platform.users.application.search;

import platform.shared.domain.UserId;
import platform.shared.domain.criteria.*;
import platform.users.application.UsersQueryResult;
import platform.users.domain.User;
import platform.users.domain.UserRepository;

import java.util.List;

public class UserSearcher {
    private final UserRepository repository;

    public UserSearcher(UserRepository repository) {
        this.repository = repository;
    }

    public UsersQueryResult searchBySimilarInterests(UserId userId) throws Exception {
        // TODO: Actually take the interests by using the userId
        List<User> users = repository.matching(new Criteria(new Filters(Filter.by("id", FilterOperator.NOT_EQUAL, userId.value())), Orders.none()));
        return UsersQueryResult.from(users);
    }
}
