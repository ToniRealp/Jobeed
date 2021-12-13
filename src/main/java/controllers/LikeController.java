package controllers;

import controllers.utils.Request;
import controllers.utils.Response;
import controllers.utils.ViewController;
import org.apache.velocity.context.Context;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.tweet.application.like.LikeTweetCommand;

public class LikeController extends ViewController {
    protected LikeController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "home.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void post(Request request, Response response, Context context) throws Exception {
        String tweetId = request.getString("tweetId");
        dispatch(new LikeTweetCommand(tweetId));
        response.redirect("home");
    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {

    }
}
