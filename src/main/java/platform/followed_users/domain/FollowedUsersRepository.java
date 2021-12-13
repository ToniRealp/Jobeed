package platform.followed_users.domain;

import platform.shared.domain.UserId;
import platform.users.domain.UserEmail;
import platform.users.domain.UserEmployment;
import platform.users.domain.UserName;
import platform.users.domain.UserPicture;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FollowedUsersRepository {
    void save(UserId followerId, UserId followedId, UserEmail email,UserName name, UserPicture picture, Date birthday, UserEmployment employment) throws Exception;
    void delete(UserId followedId, UserId followedId1) throws Exception;
    Optional<FollowedUser> search(UserId followerId, UserId followedId) throws SQLException, Exception;
    List<FollowedUser> searchAll(UserId id) throws Exception;

}
