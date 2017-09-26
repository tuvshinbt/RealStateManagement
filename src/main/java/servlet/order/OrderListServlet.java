package servlet.order;

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
import models.Order;
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

@WebServlet(urlPatterns = "/order/list")
public class OrderListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("ContextPath", getServletContext().getContextPath());
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User loggedUser = RequestUtil.getSessionCurrentUser(req);
		if (loggedUser != null) {
			req.setAttribute("mainMenu", "Orders");
			int accountId = 0, sellerId = 0;
			if (loggedUser.getRole() == OHRT.ROLE.BUYER) {
				accountId = loggedUser.getId();
			} else if (loggedUser.getRole() == OHRT.ROLE.SELLER) {
				sellerId = loggedUser.getId();
			}
			List<Order> orderList = new ArrayList<Order>();
			try {
				orderList = OrderDAO.getOrderListfind(0, accountId, 0, 0, sellerId, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("orderList", orderList);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/order/order_list.jsp");
			rd.forward(req, res);
		} else {
			req.setAttribute("msg", "Please login to continue.");
			req.getSession().setAttribute("redirecturl", getServletContext().getAttribute("ContextPath") + "/order/list");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}
	}

}
