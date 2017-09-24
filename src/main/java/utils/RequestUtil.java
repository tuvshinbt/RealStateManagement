package utils;

import models.User;
import spark.*;

public class RequestUtil {
	public static User getSessionCurrentUser(Request request) {
        return (User)request.session().attribute("currentUser");
    }
	
	public static String getRedirectUrl(Request request) {
        return (String) request.session().attribute("redirecturl");
    }
}
