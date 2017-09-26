package servlet.book.appointment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BookAppointment;
import models.dao.BookAppointmentDAO;

/**
 * Servlet implementation class BookAppointmentListServlet
 */
@WebServlet("/bookappointment/list")
public class BookAppointmentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAppointmentListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BookAppointment> appointments = new ArrayList<BookAppointment>();
		try {
			appointments = BookAppointmentDAO.findByStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("appointments", appointments);
		RequestDispatcher rq = getServletContext().getRequestDispatcher("/bookappointment/book_appointment_list.jsp");
		rq.forward(request, response);
	}

}
