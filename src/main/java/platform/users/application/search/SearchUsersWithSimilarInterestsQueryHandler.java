package platform.users.application.search;

import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryHandler;
import platform.users.application.UsersQueryResult;

public class SearchUsersWithSimilarInterestsQueryHandler implements QueryHandler<SearchUsersWithSimilarInterestsQuery, UsersQueryResult> {
    private final UserSearcher searcher;

    public SearchUsersWithSimilarInterestsQueryHandler(UserSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public UsersQueryResult handle(SearchUsersWithSimilarInterestsQuery query) throws Exception {
        UserId userId = new UserId(query.userId());
        return searcher.searchBySimilarInterests(userId);
    }
}
