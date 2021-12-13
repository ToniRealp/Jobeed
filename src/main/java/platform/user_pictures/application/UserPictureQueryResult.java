package platform.user_pictures.application;

import platform.shared.domain.query.QueryResult;

public class UserPictureQueryResult implements QueryResult {
    private final String picture;

    public UserPictureQueryResult(String picture) {
        this.picture = picture;
    }

    public String picture() {
        return picture;
    }
}
