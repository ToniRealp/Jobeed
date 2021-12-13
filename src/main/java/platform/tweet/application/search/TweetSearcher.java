package platform.tweet.application.search;

import platform.followed_users.application.FollowedUserQueryResult;
import platform.followed_users.application.FollowedUsersQueryResult;
import platform.shared.domain.criteria.*;
import platform.shared.domain.query.QueryBus;
import platform.tweet.domain.Tweet;
import platform.tweet.domain.TweetId;
import platform.tweet.domain.TweetRepository;
import platform.followed_users.application.search.SearchFollowedUsersQuery;
import platform.shared.domain.UserId;

import java.util.List;
import java.util.stream.Collectors;

public class TweetSearcher {
    private final TweetRepository repository;
    private final QueryBus queryBus;

    private static final Orders TRENDING_ORDERS = new Orders(Order.desc("createdOn"), Order.desc("likeCount"));

    public TweetSearcher(TweetRepository repository, QueryBus queryBus) {
        this.repository = repository;
        this.queryBus = queryBus;
    }

    public List<Tweet> searchLastPopularTweets() {
        Filters filters = new Filters(Filter.by("parentTweetId", FilterOperator.EQUAL, null));
        Criteria criteria = new Criteria(filters, TRENDING_ORDERS);
        return repository.matching(criteria);
    }

    public List<Tweet> searchTrendingTweetsOfContacts(UserId userId) throws Exception {
        List<String> contactsIds = getContactsIds(userId);
        Filters filters = new Filters(Filter.by("authorId", FilterOperator.IN, contactsIds), Filter.by("parentTweetId", FilterOperator.EQUAL, null));
        Criteria criteria = new Criteria(filters, TRENDING_ORDERS);
        return repository.matching(criteria);
    }

    private List<String> getContactsIds(UserId userId) throws Exception {
        FollowedUsersQueryResult followedUsersQueryResult = queryBus.ask(new SearchFollowedUsersQuery(userId.value()));
        List<String> followedUserIds = followedUsersQueryResult.followedUsers().stream().map(FollowedUserQueryResult::id).collect(Collectors.toList());
        followedUserIds.add(userId.value());
        return followedUserIds;
    }

    public List<Tweet> searchByAuthor(UserId authorId) {
        Criteria criteria = new Criteria(new Filters(Filter.by("authorId", FilterOperator.EQUAL, authorId.value())), TRENDING_ORDERS);
        return repository.matching(criteria);
    }

    public List<Tweet> searchReplies(TweetId tweetId) {
        Criteria criteria = new Criteria(new Filters(Filter.by("parentTweetId", FilterOperator.EQUAL, tweetId.value())), TRENDING_ORDERS);
        return repository.matching(criteria);
    }
}
