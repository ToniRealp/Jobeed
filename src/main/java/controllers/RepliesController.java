package controllers;

import controllers.utils.Request;
import controllers.utils.Response;
import controllers.utils.ViewController;
import org.apache.velocity.context.Context;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.tweet.application.TweetQueryResult;
import platform.tweet.application.TweetsQueryResult;
import platform.tweet.application.create.CreateTweetCommand;
import platform.tweet.application.delete.DeleteTweetCommand;
import platform.tweet.application.find.FindTweetByIdQuery;
import platform.tweet.application.search.SearchTweetsRepliesQuery;
import platform.tweet.application.update.UpdateTweetCommand;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;

public class RepliesController extends ViewController {

    public RepliesController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "replies.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            UserQueryResult userQuery = query(new FindUserByIdQuery(request.getLoggedUserId()));
            context.put("currentUser", userQuery);
            context.put("isLoggedIn", true);
        } else {
            context.put("isLoggedIn", false);
        }

        String tweetId = request.getString("tweetId");
        TweetsQueryResult tweetsQuery = query(new SearchTweetsRepliesQuery(tweetId));
        TweetQueryResult tweet = query(new FindTweetByIdQuery(tweetId));
        context.put("tweets", tweetsQuery.tweets());
        context.put("tweet", tweet);
    }

    @Override
    protected void post(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            dispatch(new CreateTweetCommand(
                    request.getString("id"),
                    request.getString("content"),
                    request.getLoggedUserId(),
                    request.getString("parentTweetId")
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
