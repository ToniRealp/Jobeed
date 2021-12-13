package platform.followed_users.application.search;

import platform.followed_users.application.FollowedUserQueryResult;
import platform.followed_users.domain.FollowedUser;
import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryHandler;

import java.util.Optional;

public class SearchFollowedUserQueryHandler implements QueryHandler<SearchFollowedUserQuery, FollowedUserQueryResult> {
    private final FollowedUsersSearcher searcher;

    public SearchFollowedUserQueryHandler(FollowedUsersSearcher searcher) {
        this.searcher = searcher;
    }


    @Override
    public FollowedUserQueryResult handle(SearchFollowedUserQuery query) throws Exception {
        Optional<FollowedUser> followedUser = searcher.searchFollowedUser(new UserId(query.followerId()), new UserId(query.followedId()));
        return followedUser.map(FollowedUserQueryResult::from).orElse(null);
    }
}
