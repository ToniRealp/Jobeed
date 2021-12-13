package platform.users.application.find;

import platform.shared.domain.query.QueryHandler;
import platform.users.application.UserQueryResult;
import platform.users.domain.User;
import platform.shared.domain.UserId;

public class FindUserByIdQueryHandler implements QueryHandler<FindUserByIdQuery, UserQueryResult> {
    private final UserFinder finder;

    public FindUserByIdQueryHandler(UserFinder finder) {
        this.finder = finder;
    }

    @Override
    public UserQueryResult handle(FindUserByIdQuery query) throws Exception {
        UserId id = new UserId(query.id());
        User user = finder.findById(id);
        return UserQueryResult.from(user);
    }
}
