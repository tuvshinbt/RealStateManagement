package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.ViewUtil;

@WebServlet(urlPatterns = "/index")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Map<String, Object> map = new HashMap<String, Object>();
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
		req.setAttribute("testVal", "hello JSP");
		System.out.println("Hello index page - " + req.getAttribute("testVal"));
		req.setAttribute("now", new Date());
		req.setAttribute("epoch", new Date(0));

		ServletContext context = getServletContext();
		RequestDispatcher rq = context.getRequestDispatcher("/index.jsp");
		rq.forward(req, res);
	}

	public static Route getLayoutPage = (Request rq, Response rs) -> {
		// if ( !isInitiated(rq) ) {
		// rs.redirect("/home");
		// return null;
		// }

		Map<String, Object> context = new HashMap<>();

		return ViewUtil.render(rq, context, Path.Template.LAYOUT);
	};

	// Hello
	public static Route initHome = (Request rq, Response rs) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		if (hotProperties != null) {
			hotProperties.addAll(hotProperties);
			hotProperties.addAll(hotProperties);
		}
		map.put("hotProperties", hotProperties);
		return ViewUtil.render(rq, map, Path.Template.INDEX);
	};

	public static boolean isInitiated(Request rq) {
		return rq.session().attribute("userId") != null;
	}
}
