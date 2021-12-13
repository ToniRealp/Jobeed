package controllers.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response {
    private final HttpServletResponse response;

    public Response(HttpServletResponse request) {
        this.response = request;
    }

    public void redirect(String view) {
        try {
            response.sendRedirect(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
