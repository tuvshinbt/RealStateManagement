package servlet.order;

import java.io.IOException;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import constants.OHRT;
import models.BuyOrder;
import models.Order;
import models.User;
import models.dao.OrderDAO;
import utils.RequestUtil;

@WebServlet(urlPatterns = "/order")
public class OrderItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String orderId = req.getParameter("orderId");

		User loggedUser = RequestUtil.getSessionCurrentUser(req);
		if (loggedUser != null) {
			List<Order> list;
			try {
				list = OrderDAO.getOrderListfind(Integer.parseInt(orderId), 0, 0, 0, 0, null);
				Order order = null;
				if (list != null && !list.isEmpty()) {
					order = (Order) list.get(0);
					req = initOrderMap(req, order);
				}
				req.setAttribute("order", order);
			} catch (Exception e) {
				e.printStackTrace();
			}

			RequestDispatcher rq = getServletContext().getRequestDispatcher("/order/order_item.jsp");
			rq.forward(req, res);
		} else {
			req.setAttribute("msg", "Please login to continue.");
			req.getSession().setAttribute("redirecturl", getServletContext().getAttribute("ContextPath") + "/order?orderId=" + orderId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}
	}

	
	public static HttpServletRequest initOrderMap(HttpServletRequest req, Order order) {

		if (order instanceof BuyOrder) {
			req.setAttribute("orderType", OHRT.ORDER.TYPE.BUY);
		} else {
			req.setAttribute("orderType", OHRT.ORDER.TYPE.RENT);
		}
		String statusName = "";
		switch (order.getStatus()) {
		case 1:
			statusName = "PENDING";
			break;
		case 2:
			statusName = "APPROVED";
			break;
		case 3:
			statusName = "BOUGHT";
			break;
		case 4:
			statusName = "RENTED";
			break;
		case 5:
			statusName = "CANCELLED";
			break;
		case 6:
			statusName = "DELETED";
			break;
		default:
			break;
		}
		req.setAttribute("statusName", statusName);
		return req;
	}

}
