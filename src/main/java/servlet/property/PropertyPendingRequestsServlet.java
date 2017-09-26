package servlet.property;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Property;
import models.dao.PropertyDAO;

/**
 * Servlet implementation class PropertyPendingRequests
 */
@WebServlet("/property/requests")
public class PropertyPendingRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertyPendingRequestsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Property> properties = new ArrayList<Property>();

		try {
			properties = PropertyDAO.getPropertyList(0, null, 0, 1, 0, null, 0, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("properties", properties);
		RequestDispatcher rq = getServletContext().getRequestDispatcher("/property/pending_requests.jsp");
		rq.forward(request, response);
	}

}
