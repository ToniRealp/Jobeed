package controllers;

import controllers.utils.ViewController;
import controllers.utils.Request;
import controllers.utils.Response;
import org.apache.velocity.context.Context;
import platform.interests.InterestsQueryResult;
import platform.interests.application.SearchAllInterestsQuery;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;
import platform.users.application.register.RegisterUserCommand;

public class RegisterController extends ViewController {

    public RegisterController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    public String view() {
        return "register.vm";
    }

    @Override
    public void get(Request request, Response response, Context context) throws Exception {
        InterestsQueryResult interests = query(new SearchAllInterestsQuery());
        context.put("interests", interests.getInterests());
    }

    @Override
    public void post(Request request, Response response, Context context) throws Exception {
        dispatch(new RegisterUserCommand(
                request.getString("id"),
                request.getString("name"),
                request.getString("email"),
                request.getString("password"),
                request.getString("confirmPassword"),
                request.getDate("birthday"),
                request.getString("gender"),
                request.getString("employment"),
                request.getValues("interests")
        ));

        response.redirect("login");
    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {

    }

}
