package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.OHRT;
import models.Property;
import models.dao.PropertyDAO;

@WebServlet(urlPatterns = "/index")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		try {
			hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (hotProperties != null) {
			hotProperties.addAll(hotProperties);
			hotProperties.addAll(hotProperties);
		}
		req.setAttribute("hotProperties", hotProperties);
		ServletContext context = getServletContext();
		RequestDispatcher rq = context.getRequestDispatcher("/index.jsp");
		rq.forward(req, res);
	}
}
