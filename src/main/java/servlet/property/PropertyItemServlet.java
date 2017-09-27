package servlet.property;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import constants.OHRT;
import models.Property;
import models.PropertyFeedback;
import models.User;
import models.dao.PropertyDAO;
import models.dao.PropertyFeedbackDAO;
import models.web.property.PropertyModel;
import utils.RequestUtil;

@WebServlet(urlPatterns = "/property")
public class PropertyItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PropertyModel propertyModel = new PropertyModel();

		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		try {
			hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		propertyModel.setHotPropertyList(hotProperties);

		// main Property
		try {
			Property mainProperty = PropertyDAO.findById(Integer.parseInt(req.getParameter("id")));
			if (mainProperty != null) {
				propertyModel.setProperty(mainProperty);
				List<PropertyFeedback> propertyFeedbackList;
				try {
					propertyFeedbackList = PropertyFeedbackDAO.findActiveByPropertyId(mainProperty.getId());
					propertyModel.setPropertyFeedbackList(propertyFeedbackList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		req.setAttribute("hotProperties", hotProperties);
		req.setAttribute("propertyModel", propertyModel);

		RequestDispatcher rq = getServletContext().getRequestDispatcher("/property/property_item.jsp");
		rq.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int propertyId = Integer.parseInt(req.getParameter("feedbackPropertyId"));
		User user = RequestUtil.getSessionCurrentUser(req);
		if (user != null) {
			try {
				PropertyFeedback feedback = new PropertyFeedback();
				feedback.setAccount(user);
				feedback.setComment(req.getParameter("comment"));
				feedback.setProperty(PropertyDAO.findById(propertyId));
				feedback.setRegisterDate(new Date());
				feedback.setStatus(1);
				PropertyFeedbackDAO.save(feedback);
				// res.sendRedirect(getServletContext().getAttribute("ContextPath")
				// + "/property?id=" + propertyId);
				res.setContentType("application/json");
				res.getWriter().write(gson.toJson(feedback));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("msg", "Please login to continue.");
			req.getSession().setAttribute("redirecturl",
					getServletContext().getAttribute("ContextPath") + "/property?id=" + propertyId + "#feedbackId");
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
//			rd.forward(req, res);
//			res.sendRedirect("/login.jsp");
			res.setContentType("application/json");
			res.getWriter().write("{\"redirect\" : \"login.jsp\"}");
		}
	}
}
