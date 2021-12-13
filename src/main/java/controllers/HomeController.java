package controllers;

import controllers.utils.ViewController;
import controllers.utils.Request;
import controllers.utils.Response;
import org.apache.velocity.context.Context;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.tweet.application.TweetsQueryResult;
import platform.tweet.application.create.CreateTweetCommand;
import platform.tweet.application.delete.DeleteTweetCommand;
import platform.tweet.application.search.SearchTrendingTweetsQuery;
import platform.tweet.application.search.SearchTrendingTweetsOfContactsQuery;
import platform.tweet.application.update.UpdateTweetCommand;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;

public class HomeController extends ViewController {

    public HomeController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "home.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            TweetsQueryResult tweetsQuery = query(new SearchTrendingTweetsOfContactsQuery(request.getLoggedUserId()));
            UserQueryResult userQuery = query(new FindUserByIdQuery(request.getLoggedUserId()));
            context.put("tweets", tweetsQuery.tweets());
            context.put("currentUser", userQuery);
            context.put("isLoggedIn", true);
        } else {
            TweetsQueryResult query = query(new SearchTrendingTweetsQuery());
            context.put("tweets", query.tweets());
            context.put("isLoggedIn", false);
        }
    }

    @Override
    protected void post(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            dispatch(new CreateTweetCommand(
                    request.getString("id"),
                    request.getString("content"),
                    request.getLoggedUserId(),
                    null
            ));
        }
    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            dispatch(new UpdateTweetCommand(request.getString("id"), request.getString("content"), request.getLoggedUserId()));
        }
    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            dispatch(new DeleteTweetCommand(request.getString("id"), request.getLoggedUserId()));
        }
    }

}
