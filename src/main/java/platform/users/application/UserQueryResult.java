package platform.users.application;

import platform.shared.domain.query.QueryResult;
import platform.users.domain.User;

import java.util.Date;

public class UserQueryResult implements QueryResult {
    private final String id;
    private final String name;
    private final String email;
    private final String picture;
    private final Date birthday;
    private final String gender;
    private final String employment;
    private final String role;

    public UserQueryResult(String id, String name, String email, String picture, Date birthday, String gender, String employment, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.birthday = birthday;
        this.gender = gender;
        this.employment = employment;
        this.role = role;
    }

    public static UserQueryResult from(User user) {
        return new UserQueryResult(user.id().value(), user.name().value(), user.email().value(), user.picture().value(), user.birthday(), user.gender().toString(), user.employment().toString(), user.role().value());
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String email() {
        return this.email;
    }

    public Date birthday() {
        return this.birthday;
    }

    public String gender() {
        return this.gender;
    }

    public String employment() {
        return this.employment;
    }

    public String picture() {
        return picture;
    }

    public String role() {
        return role;
    }
}
