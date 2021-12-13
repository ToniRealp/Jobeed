package platform.followed_users.application;

import platform.followed_users.domain.FollowedUser;
import platform.shared.domain.query.QueryResult;

import java.util.Date;

public class FollowedUserQueryResult implements QueryResult {
    private final String id;
    private final String name;
    private final String email;
    private final String picture;
    private final Date birthday;
    private final String employment;


    public FollowedUserQueryResult(String id, String name, String email, String picture, Date birthday, String employment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.birthday = birthday;
        this.employment = employment;
    }

    public static FollowedUserQueryResult from(FollowedUser followedUser){
        return new FollowedUserQueryResult(followedUser.id().value(), followedUser.name().value(), followedUser.email().value(), followedUser.picture().value(), followedUser.birthday(), followedUser.employment().toString());
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String picture() {
        return picture;
    }

    public Date birthday() {
        return birthday;
    }

    public String employment() {
        return employment;
    }
}
