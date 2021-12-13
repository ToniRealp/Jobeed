package platform.followed_users.domain;

import platform.shared.domain.UserId;
import platform.users.domain.UserEmail;
import platform.users.domain.UserEmployment;
import platform.users.domain.UserName;
import platform.users.domain.UserPicture;

import java.util.Date;

public class FollowedUser {
    private final UserId id;
    private final UserName name;
    private final UserEmail email;
    private final UserPicture picture;
    private final Date birthday;
    private final UserEmployment employment;

    public FollowedUser(UserId id, UserName name, UserEmail email, UserPicture picture, Date birthday, UserEmployment employment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.birthday = birthday;
        this.employment = employment;
    }

    public UserId id() {
        return id;
    }

    public UserName name() {
        return name;
    }

    public UserEmail email() {
        return email;
    }

    public UserPicture picture() {
        return picture;
    }

    public Date birthday() {
        return birthday;
    }

    public UserEmployment employment() {
        return employment;
    }
}
