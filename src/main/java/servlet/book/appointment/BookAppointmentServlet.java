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
@WebServlet("/property/bookappointment")
public class BookAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAppointmentServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String apptDate = request.getParameter("appointment_date");
		String apptInfo = request.getParameter("appointment_info");
		String propertyID = request.getParameter("property_id");

		User user = RequestUtil.getSessionCurrentUser(request);
		if (user == null) {
			request.setAttribute("msg", "Please login in order to book appointment!");
			request.getSession().setAttribute("redirecturl",
					getServletContext().getAttribute("ContextPath") + "/property?id=" + propertyID);
			ServletContext context = getServletContext();
			RequestDispatcher rq = context.getRequestDispatcher("/login.jsp");
			rq.forward(request, response);
		} else {
			boolean result = BookAppointment.requestBookAppointment(propertyID, apptDate, apptInfo, user.getId());
			if (result) {
				request.setAttribute("msgOk", "Successfully booked.");
			} else {
				request.setAttribute("msg", "Failed to book appointment!");
			}
			response.sendRedirect("../property?id=" + propertyID);
		}
	}

}
