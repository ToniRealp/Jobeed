package platform.user_pictures.application.find;

import platform.shared.domain.query.QueryHandler;
import platform.user_pictures.application.UserPictureQueryResult;

public class FindUserPictureQueryHandler implements QueryHandler<FindUserPictureQuery, UserPictureQueryResult> {
    private final UserPictureFinder finder;

    public FindUserPictureQueryHandler(UserPictureFinder finder) {
        this.finder = finder;
    }

    @Override
    public UserPictureQueryResult handle(FindUserPictureQuery query) throws Exception {
        return new UserPictureQueryResult(finder.findByEmail(query.email()));
    }
}
