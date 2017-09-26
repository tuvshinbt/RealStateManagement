package servlet.property;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BookAppointment;
import models.Property;
import utils.Path;
import utils.RequestUtil;

/**
 * Servlet implementation class PropertyPendingApproveServlet
 */
@WebServlet("/property/approve")
public class PropertyPendingApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertyPendingApproveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int approvedBy = RequestUtil.getSessionCurrentUser(request).getId();
		Property.approveProperty(id, approvedBy);
		response.sendRedirect("requests");
	}

}
