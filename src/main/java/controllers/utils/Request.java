package controllers.utils;


import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Request {
    private final HttpServletRequest request;

    public Request(HttpServletRequest request) {
        this.request = request;
    }

    public boolean isHttpMethod(String method) {
        Boolean methodInRequest = method.equalsIgnoreCase(request.getParameter("method"));
        Boolean methodInHeader = method.equalsIgnoreCase(request.getMethod());
        return methodInHeader || methodInRequest;
    }

    public String getString(String name) {
        return request.getParameter(name);
    }

    public List<String> getValues(String name) {
        String[] values = request.getParameterValues(name);
        return null == values ? new ArrayList<>() : Arrays.asList(values);
    }

    public Date getDate(String name) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(getString(name));
    }

    public void clearSession() {
        request.getSession().invalidate();
    }

    public void setLoggedUserId(String id) {
        request.getSession().setAttribute("userId", id);
    }

    public String getLoggedUserId() {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            return null;
        }
        return String.valueOf(userId);
    }

    public boolean isAuthenticated() {
        return getLoggedUserId() != null;
    }
}
