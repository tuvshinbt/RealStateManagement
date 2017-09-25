package servlet.property;

import java.io.IOException;
import java.util.ArrayList;
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

import org.apache.velocity.tools.generic.DateTool;

import constants.OHRT;
import models.Property;
import models.PropertyFeedback;
import models.dao.PropertyDAO;
import models.dao.PropertyFeedbackDAO;
import models.web.property.PropertyModel;
import utils.Path;
import utils.ViewUtil;

@WebServlet(urlPatterns = "/property/item")
public class PropertyItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("ContextPath", getServletContext().getContextPath());
		super.init();
	}

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
					propertyFeedbackList = PropertyFeedbackDAO
							.findActiveByPropertyId(mainProperty.getId());
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

}
