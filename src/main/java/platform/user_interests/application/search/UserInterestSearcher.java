package platform.user_interests.application.search;

import platform.shared.domain.UserId;
import platform.shared.domain.criteria.*;
import platform.user_interests.domain.UserInterest;
import platform.user_interests.domain.UserInterestRepository;

import java.util.List;

public class UserInterestSearcher {
    private final UserInterestRepository repository;

    public UserInterestSearcher(UserInterestRepository repository) {
        this.repository = repository;
    }

    public List<UserInterest> searchByUser(UserId userId) {
        Criteria criteria = new Criteria(new Filters(Filter.by("userId", FilterOperator.EQUAL, userId.value())), Orders.none());
        return repository.matching(criteria);
    }
}
