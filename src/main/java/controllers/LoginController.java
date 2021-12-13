package controllers;

import controllers.utils.ViewController;
import controllers.utils.Request;
import controllers.utils.Response;
import org.apache.velocity.context.Context;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.users.application.UserQueryResult;
import platform.users.application.find.FindUserByEmailQuery;
import platform.users.application.login.LoginUserCommand;


public class LoginController extends ViewController {
    public LoginController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "login.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) {
    }

    @Override
    protected void post(Request request, Response response, Context context) throws Exception {
        dispatch(new LoginUserCommand(request.getString("email"), request.getString("password")));
        UserQueryResult user = query(new FindUserByEmailQuery(request.getString("email")));

        request.setLoggedUserId(user.id());
        response.redirect("home");
    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {

    }
}
