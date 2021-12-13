package controllers.utils;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityLayoutServlet;
import platform.shared.domain.command.Command;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.query.Query;
import platform.shared.domain.query.QueryBus;
import platform.tweet.domain.TweetContentTooLong;
import platform.users.domain.InvalidCredentials;
import platform.users.domain.PasswordsDoNotMatch;
import platform.users.domain.UserEmailAlreadyExists;
import platform.users.domain.UserNotFound;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ViewController extends VelocityLayoutServlet {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    protected ViewController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    protected abstract String view();

    protected abstract void get(Request request, Response response, Context context) throws Exception;

    protected abstract void post(Request request, Response response, Context context) throws Exception;

    protected abstract void patch(Request request, Response response, Context context) throws Exception;

    protected abstract void delete(Request request, Response response, Context context) throws Exception;

    protected void dispatch(Command command) throws Exception {
        commandBus.dispatch(command);
    }

    protected <R> R query(Query query) throws Exception {
        return queryBus.ask(query);
    }

    @Override
    protected Template handleRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Context context) {
        Request request = new Request(servletRequest);
        Response response = new Response(servletResponse);

        try {
            if (request.isHttpMethod("PATCH")) {
                patch(request, response, context);
            } else if (request.isHttpMethod("DELETE")) {
                delete(request, response, context);
            } else if (request.isHttpMethod("POST")) {
                post(request, response, context);
            }
            get(request, response, context);
        } catch (Exception exception) {
            try {
                get(request, response, context);
                context.put("error", getErrorMessage(exception));
            } catch (Exception e) {
                exception.printStackTrace();
                request.clearSession();
                context.put("error", getErrorMessage(exception));
                return getTemplate("login.vm");
            }
        }

        return getTemplate(view());
    }

    private String getErrorMessage(Exception exception) {
        if (exception instanceof UserNotFound) {
            return "User not found";
        } else if (exception instanceof UserEmailAlreadyExists) {
            return "That email is already taken";
        } else if (exception instanceof PasswordsDoNotMatch) {
            return "Passwords do not match";
        } else if (exception instanceof InvalidCredentials) {
            return "Email or password do not match an existing user";
        } else if (exception instanceof TweetContentTooLong) {
            return "Tweet content exceeds maximum number of characters";
        }
        return "Something went wrong";
    }

}
