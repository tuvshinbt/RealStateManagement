package utils;

import javax.servlet.http.HttpServletRequest;

import models.User;
import spark.*;

public class RequestUtil {
	public static User getSessionCurrentUser(Request request) {
		return (User) request.session()
				.attribute("currentUser");
	}

	public static String getRedirectUrl(Request request) {
		return (String) request.session()
				.attribute("redirecturl");
	}

	/**
	 * getSessionCurrentUser
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @return
	 */
	public static User getSessionCurrentUser(HttpServletRequest request) {
		return (User) request.getSession()
				.getAttribute("currentUser");
	}

	/**
	 * getRedirectUrl
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @return
	 */
	public static String getRedirectUrl(HttpServletRequest request) {
		return (String) request.getSession()
				.getAttribute("redirecturl");
	}
}
