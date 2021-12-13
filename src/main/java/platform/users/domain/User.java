package platform.users.domain;


import platform.shared.domain.UserId;

import java.util.Date;

public class User {
    private final UserId id;
    private final UserName name;
    private final UserEmail email;
    private final UserPassword password;
    private final UserPicture picture;
    private final Date birthday;
    private final UserGender gender;
    private final UserEmployment employment;
    private final UserRole role;

    public User(UserId id, UserName name, UserEmail email, UserPassword password, UserPicture picture, Date birthday, UserGender gender, UserEmployment employment, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.birthday = birthday;
        this.gender = gender;
        this.employment = employment;
        this.role = role;
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

    public UserPassword password() {
        return password;
    }

    public Date birthday() {
        return birthday;
    }

    public UserGender gender() {
        return gender;
    }

    public UserEmployment employment() {
        return employment;
    }

    public UserPicture picture() {
        return picture;
    }

    public UserRole role() {
        return role;
    }
}
