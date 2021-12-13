package platform.users.application.find;

import platform.shared.domain.query.QueryHandler;
import platform.users.application.UserQueryResult;
import platform.users.domain.User;
import platform.users.domain.UserEmail;

public class FindUserByEmailQueryHandler implements QueryHandler<FindUserByEmailQuery, UserQueryResult> {
    private final UserFinder finder;

    public FindUserByEmailQueryHandler(UserFinder finder) {
        this.finder = finder;
    }

    @Override
    public UserQueryResult handle(FindUserByEmailQuery query) throws Exception {
        UserEmail email = new UserEmail(query.email());
        User user = finder.findByEmail(email);
        return UserQueryResult.from(user);
    }
}
