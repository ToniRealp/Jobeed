package controllers;

import controllers.utils.Request;
import controllers.utils.Response;
import controllers.utils.ViewController;
import org.apache.velocity.context.Context;
import platform.followed_users.application.FollowedUsersQueryResult;
import platform.followed_users.application.search.SearchFollowedUsersQuery;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByIdQuery;

public class FollowedController extends ViewController {
    protected FollowedController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "followed.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            FollowedUsersQueryResult followedUsers = query(new SearchFollowedUsersQuery(request.getLoggedUserId()));
            UserQueryResult currentUser = query(new FindUserByIdQuery(request.getLoggedUserId()));
            context.put("followedUsers", followedUsers.followedUsers());
            context.put("isLoggedIn", true);
            context.put("currentUser", currentUser);
        }
    }

    @Override
    protected void post(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {

    }
}
