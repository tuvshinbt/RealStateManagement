package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * User registration Controller
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int role = Integer.parseInt(request.getParameter("role"));
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String address = request.getParameter("address");

		System.out.println(firstName + ", " + lastName + ", " + email + ", " + password + ", " + confirm_password + ", "
				+ address);

		if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()
				|| confirm_password.isEmpty() || address.isEmpty()) {
			request.setAttribute("msg", "All field required");
			RequestDispatcher rq = getServletContext().getRequestDispatcher("/register.jsp");
			rq.forward(request, response);
		}

		User isExist = User.exitst(email);

		if (isExist == null) {
			User.create(firstName, lastName, email, phone, password, address, role);
			response.sendRedirect("property/list");
		} else {
			response.sendRedirect("register.jsp");
		}
	}

}
