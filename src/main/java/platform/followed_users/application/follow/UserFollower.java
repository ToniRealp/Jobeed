package platform.followed_users.application.follow;

import platform.followed_users.domain.FollowedUsersRepository;
import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryBus;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;
import platform.users.domain.UserEmail;
import platform.users.domain.UserEmployment;
import platform.users.domain.UserName;
import platform.users.domain.UserPicture;

import java.util.Date;

public class UserFollower {

    FollowedUsersRepository followedUsersRepository;
    private final QueryBus queryBus;

    public UserFollower(FollowedUsersRepository followedUsersRepository, QueryBus queryBus) {
        this.followedUsersRepository = followedUsersRepository;
        this.queryBus = queryBus;
    }

    public void follow(UserId followerId, UserId followedId) throws Exception {
        UserQueryResult followedUser = queryBus.ask(new FindUserByIdQuery(followedId.value()));
        UserEmail email = new UserEmail(followedUser.email());
        UserName name = new UserName(followedUser.name());
        UserPicture picture = new UserPicture(followedUser.picture());
        Date birthday = followedUser.birthday();
        UserEmployment employment = UserEmployment.valueOf(followedUser.employment());

        followedUsersRepository.save(followerId, followedId, email, name, picture, birthday, employment);
    }

    public void unFollow(UserId followerId, UserId followedId) throws Exception {
        followedUsersRepository.delete(followerId, followedId);
    }
}
