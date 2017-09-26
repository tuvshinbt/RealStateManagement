package servlet.order;

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

import org.apache.velocity.tools.generic.DateTool;

import constants.OHRT;
import models.Order;
import models.OrderFactory;
import models.Property;
import models.PropertyFeedback;
import models.User;
import models.dao.OrderDAO;
import models.dao.PropertyDAO;
import models.dao.PropertyFeedbackDAO;
import models.web.property.PropertyModel;
import utils.Path;
import utils.RequestUtil;
import utils.ViewUtil;

@WebServlet(urlPatterns = "/order/register")
public class OrderRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("ContextPath", getServletContext().getContextPath());
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("propertyId", req.getParameter("propertyId"));
		req.setAttribute("orderType", req.getParameter("orderType"));

		RequestDispatcher rq = getServletContext().getRequestDispatcher("/order/order_register.jsp");
		rq.forward(req, res);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer propertyId = Integer.parseInt(req.getParameter("propertyId"));
		String orderType = req.getParameter("orderType");
		Double offerBuyAmount = req.getParameter("offerBuyAmount") != null
				? Double.parseDouble(req.getParameter("offerBuyAmount")) : null;
		Integer offerRentMonths = req.getParameter("offerRentMonths") != null
				? Integer.parseInt(req.getParameter("offerRentMonths")) : null;
		Double offerRentPerMonthAmount = req.getParameter("offerRentPerMonthAmount") != null
				? Double.parseDouble(req.getParameter("offerRentPerMonthAmount")) : null;
		Double offerRentDespositAmount = req.getParameter("offerRentDespositAmount") != null
				? Double.parseDouble(req.getParameter("offerRentDespositAmount")) : null;
		Boolean offerIsExtend = req.getParameter("offerIsExtend") != null
				? Boolean.parseBoolean(req.getParameter("offerIsExtend")) : null;
		String offerDescription = req.getParameter("offerDescription");

		User loggedUser = RequestUtil.getSessionCurrentUser(req);
		if (loggedUser != null) {
			try {
				Order order = null;
				if (orderType.equalsIgnoreCase(OHRT.ORDER.TYPE.RENT)) {
					// Rent
					order = OrderFactory.createRentOrder(0, loggedUser, new Date(), PropertyDAO.findById(propertyId),
							OHRT.ORDER.STATUS.PENDING, PropertyDAO.findById(propertyId).getOwner(), offerDescription,
							offerRentMonths, offerRentPerMonthAmount, offerRentDespositAmount, offerIsExtend);
				} else {
					// Buy
					order = OrderFactory.createBuyOrder(0, loggedUser, new Date(), PropertyDAO.findById(propertyId),
							OHRT.ORDER.STATUS.PENDING, PropertyDAO.findById(propertyId).getOwner(), offerDescription,
							offerBuyAmount);
				}
				OrderDAO.save(order);
			} catch (Exception e) {
				e.printStackTrace();
			}
			res.sendRedirect(getServletContext().getAttribute("ContextPath") + "/order/list");
		} else {
			req.setAttribute("msg", "Please login to continue.");
			req.getSession().setAttribute("redirecturl", getServletContext().getAttribute("ContextPath")
					+ "/order/register?propertyId=" + propertyId + "&orderType=" + orderType);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}

	}

}
