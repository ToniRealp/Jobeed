package platform.user_interests.application.search;

import platform.shared.domain.UserId;
import platform.shared.domain.query.QueryHandler;
import platform.user_interests.application.UserInterestsQueryResult;
import platform.user_interests.domain.UserInterest;

import java.util.List;

public class SearchUserInterestsQueryHandler implements QueryHandler<SearchUserInterestsQuery, UserInterestsQueryResult> {
    private final UserInterestSearcher searcher;

    public SearchUserInterestsQueryHandler(UserInterestSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public UserInterestsQueryResult handle(SearchUserInterestsQuery query) throws Exception {
        List<UserInterest> interests = searcher.searchByUser(new UserId(query.userId()));
        return UserInterestsQueryResult.from(interests);
    }
}
