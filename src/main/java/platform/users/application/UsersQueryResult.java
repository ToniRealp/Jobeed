package platform.users.application;

import platform.shared.domain.query.QueryResult;
import platform.users.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UsersQueryResult implements QueryResult {
    private final List<UserQueryResult> users;

    public UsersQueryResult(List<UserQueryResult> users) {
        this.users = users;
    }

    public static UsersQueryResult from(List<User> users) {
        return new UsersQueryResult(users.stream().map(UserQueryResult::from).collect(Collectors.toList()));
    }

    public List<UserQueryResult> users() {
        return users;
    }
}
