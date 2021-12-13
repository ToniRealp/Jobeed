package platform.followed_users.application.search;

import platform.followed_users.domain.FollowedUser;
import platform.followed_users.domain.FollowedUsersRepository;
import platform.shared.domain.UserId;

import java.util.List;
import java.util.Optional;

public class FollowedUsersSearcher {
    private final FollowedUsersRepository repository;

    public FollowedUsersSearcher(FollowedUsersRepository repository) {
        this.repository = repository;
    }

    public Optional<FollowedUser> searchFollowedUser(UserId followerId, UserId followedId) throws Exception {
        Optional<FollowedUser> followedUser = repository.search(followerId, followedId);
        return followedUser;
    }

    public List<FollowedUser> searchFollowedUsers(UserId userId) throws Exception {
        List<FollowedUser> followedUsers = repository.searchAll(userId);
        return followedUsers;
    }
}
