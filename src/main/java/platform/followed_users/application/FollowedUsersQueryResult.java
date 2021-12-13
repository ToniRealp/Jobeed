package platform.followed_users.application;

import platform.followed_users.domain.FollowedUser;
import platform.shared.domain.query.QueryResult;

import java.util.List;
import java.util.stream.Collectors;

public class FollowedUsersQueryResult implements QueryResult {

    private final List<FollowedUserQueryResult> followedUsers;

    public FollowedUsersQueryResult(List<FollowedUserQueryResult> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public static FollowedUsersQueryResult from(List<FollowedUser> followedUsers){
        return new FollowedUsersQueryResult(followedUsers.stream().map(FollowedUserQueryResult::from).collect(Collectors.toList()));
    }

    public List<FollowedUserQueryResult> followedUsers() {
        return followedUsers;
    }
}
