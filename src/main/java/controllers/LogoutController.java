package controllers;

import controllers.utils.ViewController;
import controllers.utils.Request;
import controllers.utils.Response;
import org.apache.velocity.context.Context;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.QueryBus;

public class LogoutController extends ViewController {

    public LogoutController(CommandBus commandBus, QueryBus queryBus) {
        super(commandBus, queryBus);
    }

    @Override
    protected String view() {
        return "login.vm";
    }

    @Override
    protected void get(Request request, Response response, Context context) {
        request.clearSession();
        response.redirect("login");
    }

    @Override
    protected void post(Request request, Response response, Context context) {
    }

    @Override
    protected void patch(Request request, Response response, Context context) throws Exception {

    }

    @Override
    protected void delete(Request request, Response response, Context context) throws Exception {

    }

}
