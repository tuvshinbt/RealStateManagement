package servlet.book.appointment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BookAppointment;
import models.User;
import utils.RequestUtil;

/**
 * Servlet implementation class PropertyBookAppointmentServlet
 */
@WebServlet("/bookappointment/approve")
public class BookAppointmentApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAppointmentApproveServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		BookAppointment.approveAppointment(id);
		response.sendRedirect("list");
	}

}
