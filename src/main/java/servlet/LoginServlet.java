package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.Path;
import utils.RequestUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = User.find(email, password);
		if (user != null) {
			request.setAttribute("msg", null);
			request.getSession()
					.setAttribute("currentUser", user);
			String rurl = RequestUtil.getRedirectUrl(request);
			if (rurl != null && !rurl.equals("")) {
				request.getSession()
						.removeAttribute("redirecturl");
				response.sendRedirect(rurl);
			} else {
				response.sendRedirect("index");
			}
		} else {
			request.setAttribute("msg", "Invalid email or password");
			ServletContext context = getServletContext();
			RequestDispatcher rq = context.getRequestDispatcher("/login.jsp");
			rq.forward(request, response);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logged out.");
		request.getSession().removeAttribute("currentUser");
		request.getSession().removeAttribute("isAdmin");
		response.sendRedirect("index");
	}

}
