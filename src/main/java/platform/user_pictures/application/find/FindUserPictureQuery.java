package platform.user_pictures.application.find;

import platform.shared.domain.query.Query;

public class FindUserPictureQuery implements Query {
    private final String email;

    public FindUserPictureQuery(String email) {
        this.email = email;
    }

    public String email() {
        return email;
    }
}
