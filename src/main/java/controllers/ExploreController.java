package controllers;

import controllers.utils.Request;
import controllers.utils.Response;
import controllers.utils.ViewController;
import org.apache.velocity.context.Context;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.users.application.UserQueryResult;
import platform.users.application.UsersQueryResult;
import platform.users.application.find.FindUserByIdQuery;
import platform.users.application.search.SearchUsersWithSimilarInterestsQuery;

public class ExploreController extends ViewController {
    protected ExploreController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "explore.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) throws Exception {
        if (request.isAuthenticated()) {
            UsersQueryResult usersQuery = query(new SearchUsersWithSimilarInterestsQuery(request.getLoggedUserId()));
            UserQueryResult currentUser = query(new FindUserByIdQuery(request.getLoggedUserId()));
            context.put("users", usersQuery.users());
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
