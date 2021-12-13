package controllers;

import controllers.utils.ViewController;
import controllers.utils.Request;
import controllers.utils.Response;
import org.apache.velocity.context.Context;
import platform.followed_users.application.FollowedUserQueryResult;
import platform.followed_users.application.follow.UnFollowUserCommand;
import platform.followed_users.application.search.SearchFollowedUserQuery;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.tweet.application.TweetsQueryResult;
import platform.tweet.application.search.SearchUserTweetsQuery;
import platform.user_interests.application.UserInterestsQueryResult;
import platform.user_interests.application.search.SearchUserInterestsQuery;
import platform.users.application.UserQueryResult;
import platform.users.application.delete.DeleteUserCommand;
import platform.users.application.find.FindUserByIdQuery;
import platform.followed_users.application.follow.FollowUserCommand;
import platform.users.application.update.UpdateUserCommand;

public class ProfileController extends ViewController {
    protected ProfileController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "profile.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) throws Exception {
        String userId = request.getString("userId");
        if (request.isAuthenticated()) {
            UserQueryResult currentUser = query(new FindUserByIdQuery(request.getLoggedUserId()));
            FollowedUserQueryResult followedUser = query(new SearchFollowedUserQuery(currentUser.id(), userId));
            context.put("currentUser", currentUser);
            context.put("isLoggedIn", true);
            context.put("isFollowedUser", followedUser != null);
        } else {
            context.put("isLoggedIn", false);
        }
        UserQueryResult user = query(new FindUserByIdQuery(userId));
        TweetsQueryResult tweets = query(new SearchUserTweetsQuery(userId));
        UserInterestsQueryResult userInterests = query(new SearchUserInterestsQuery(userId));

        context.put("user", user);
        context.put("userInterests", userInterests.userInterest());
        context.put("tweets", tweets.tweets());
    }

    @Override
    protected void post(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            String followerUserId = request.getLoggedUserId();
            String followedUserId = request.getString("userId");
            FollowedUserQueryResult followedUser = query(new SearchFollowedUserQuery(followerUserId, followedUserId));
            if (followedUser != null){
                dispatch(new UnFollowUserCommand(followerUserId, followedUserId));
            }else{
                dispatch(new FollowUserCommand(followerUserId, followedUserId));
            }
        }
    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()){
            dispatch(new UpdateUserCommand(
                    request.getString("userId"),
                    request.getString("name"),
                    request.getString("email"),
                    request.getString("employment"),
                    request.getString("gender")
            ));
        }
    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {
        if(request.isAuthenticated()){
            dispatch(new DeleteUserCommand(request.getString("userId")));
            response.redirect("logout");
        }
    }

}
