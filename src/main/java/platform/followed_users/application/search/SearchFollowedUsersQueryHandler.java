package platform.followed_users.application.search;

import platform.followed_users.application.FollowedUsersQueryResult;
import platform.followed_users.domain.FollowedUser;
import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryHandler;

import java.util.List;

public class SearchFollowedUsersQueryHandler implements QueryHandler<SearchFollowedUsersQuery, FollowedUsersQueryResult> {
    private final FollowedUsersSearcher searcher;

    public SearchFollowedUsersQueryHandler(FollowedUsersSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public FollowedUsersQueryResult handle(SearchFollowedUsersQuery query) throws Exception {
        List<FollowedUser> users = searcher.searchFollowedUsers(new UserId(query.userId()));
        return FollowedUsersQueryResult.from(users);
    }
}
