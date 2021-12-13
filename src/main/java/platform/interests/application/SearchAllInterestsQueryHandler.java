package platform.interests.application;

import platform.interests.InterestsQueryResult;
import platform.interests.domain.Interest;
import platform.shared.domain.query.QueryHandler;

import java.util.List;

public class SearchAllInterestsQueryHandler implements QueryHandler<SearchAllInterestsQuery, InterestsQueryResult> {
    private final InterestSearcher searcher;

    public SearchAllInterestsQueryHandler(InterestSearcher searcher){ this.searcher = searcher; }

    @Override
    public InterestsQueryResult handle(SearchAllInterestsQuery query) throws Exception {
        List<Interest> interests = searcher.searchAll();
        return InterestsQueryResult.from(interests);
    }
}
