package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.OHRT;
import models.BuyOrder;
import models.Invoice;
import models.Order;
import models.OrderFactory;
import models.Payment;
import models.User;
import models.dao.InvoiceDAO;
import models.dao.OrderDAO;
import models.dao.PaymentDAO;
import models.dao.PropertyDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.RequestUtil;
import utils.ViewUtil;

public class OrderController {

	public static Route getOrderListPage = (Request rq, Response rs) -> {
		Map<String, Object> model = new HashMap<>();
		User loggedUser = RequestUtil.getSessionCurrentUser(rq);
		if (loggedUser != null) {
			int accountId = 0, sellerId = 0;
			if (loggedUser.getRole() == OHRT.ROLE.BUYER) {
				accountId = loggedUser.getId();
			} else if (loggedUser.getRole() == OHRT.ROLE.SELLER) {
				sellerId = loggedUser.getId();
			}
			List<Order> orderList = OrderDAO.getOrderListfind(0, accountId, 0, 0, sellerId, null);
			model.put("orderList", orderList);
			return ViewUtil.render(rq, model, Path.Template.ORDER_LIST);
		} else {
			rq.session().attribute("redirecturl", "/order/list");
			rs.redirect(Path.Web.LOGIN);
			return null;
		}
	};

	public static Route getOrderRegisterPage = (Request rq, Response rs) -> {
		String propertyId = rq.params("propertyId");
		String orderType = rq.params("type");
		Map<String, Object> context = new HashMap<>();
		context.put("propertyId", propertyId);
		context.put("orderType", orderType);
		return ViewUtil.render(rq, context, Path.Template.ORDER_REGISTER);
	};

	public static Route postOrderRegisterPage = (Request rq, Response rs) -> {
		Integer propertyId = rq.queryMap("propertyId").integerValue();
		String orderType = rq.queryMap("orderType").value();
		Double offerBuyAmount = rq.queryMap("offerBuyAmount").doubleValue();
		Integer offerRentMonths = rq.queryMap("offerRentMonths").integerValue();
		Double offerRentPerMonthAmount = rq.queryMap("offerRentPerMonthAmount").doubleValue();
		Double offerRentDespositAmount = rq.queryMap("offerRentDespositAmount").doubleValue();
		Boolean offerIsExtend = rq.queryMap("offerIsExtend").booleanValue();
		String offerDescription = rq.queryMap("offerDescription").value();
		User loggedUser = RequestUtil.getSessionCurrentUser(rq);
		if (loggedUser != null) {
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
			Map<String, Object> context = new HashMap<>();
			rs.redirect("/order/list");
			return null;
		} else {
			rq.session().attribute("redirecturl", "/order/register/" + propertyId + "/" + orderType);
			rs.redirect(Path.Web.LOGIN);
			return null;
		}
	};

	public static Route getApproveOrderPage = (Request rq, Response rs) -> {
		String orderId = rq.params("orderId");
		Map<String, Object> model = new HashMap<>();
		Invoice invoice = new Invoice();
		OrderDAO.updateStatusById(Integer.parseInt(orderId), OHRT.ORDER.STATUS.APPROVED);
		List<Order> orderList = OrderDAO.getOrderListfind(Integer.parseInt(orderId), 0, 0, 0, 0, null);
		if (orderList != null && !orderList.isEmpty()) {
			Order order = orderList.get(0);
			model.put("order", order);
			initOrderMap(model, order);
			invoice.setOrderId(Integer.parseInt(orderId));
			invoice.setAmount(order.calculatePrice());
			invoice.setStatusId(OHRT.INVOICE.STATUS.PENDING);
			InvoiceDAO.save(invoice);
		}
		String msg = "Order has been successfully approved. The invoice will be sent to buyer by email.";
		model.put("msg", msg);
		return ViewUtil.render(rq, model, Path.Template.ORDER_ITEM);
	};

	public static Route getCancelOrderPage = (Request rq, Response rs) -> {
		String orderId = rq.params("orderId");
		Map<String, Object> model = new HashMap<>();
		OrderDAO.updateStatusById(Integer.parseInt(orderId), OHRT.ORDER.STATUS.CANCELLED);

		List<Order> orderList = OrderDAO.getOrderListfind(Integer.parseInt(orderId), 0, 0, 0, 0, null);
		if (orderList != null && !orderList.isEmpty()) {
			Order order = orderList.get(0);
			model.put("order", order);
			initOrderMap(model, order);
		}
		String errorMsg = "Order has been successfully cancelled. The notification will be sent to buyer by email.";
		model.put("errorMsg", errorMsg);
		return ViewUtil.render(rq, model, Path.Template.ORDER_ITEM);
	};

	public static Route postPaymentOrderPage = (Request rq, Response rs) -> {

		Integer orderId = rq.queryMap("orderId").integerValue();
		String paymentType = rq.queryMap("paymentType").value();
		String cardType = rq.queryMap("cardType").value();

		Map<String, Object> model = new HashMap<>();

		List<Order> orderList = OrderDAO.getOrderListfind(orderId, 0, 0, 0, 0, null);
		if (orderList != null && !orderList.isEmpty()) {
			Order order = orderList.get(0);
			Invoice invoice = InvoiceDAO.findByOrderIdAndStatus(orderId, OHRT.INVOICE.STATUS.PENDING);
			Payment payment = new Payment();
			payment.setInvoiceId(invoice.getId());
			payment.setOrderId(orderId);
			payment.setAmount(order.calculatePrice());
			payment.setPaymentType(paymentType);
			payment.setCardType(cardType);
			PaymentDAO.save(payment);

			InvoiceDAO.update(invoice.getId(), OHRT.INVOICE.STATUS.PAID);
			if (order instanceof BuyOrder) {
				OrderDAO.updateStatusById(orderId, OHRT.ORDER.STATUS.BOUGHT);
				order.setStatus(OHRT.ORDER.STATUS.BOUGHT);
				PropertyDAO.updateStatusById(order.getProperty().getId(), OHRT.PROPERTY.STATUS.SOLD);
			} else {
				OrderDAO.updateStatusById(orderId, OHRT.ORDER.STATUS.RENTED);
				order.setStatus(OHRT.ORDER.STATUS.RENTED);
				PropertyDAO.updateStatusById(order.getProperty().getId(), OHRT.PROPERTY.STATUS.RENTED);
			}
			initOrderMap(model, order);
			model.put("order", order);
			String msg = "Payment has been successfully paid.";
			model.put("msg", msg);
		}
		return ViewUtil.render(rq, model, Path.Template.ORDER_ITEM);
	};

	public static void initOrderMap(Map<String, Object> model, Order order) {

		if (order instanceof BuyOrder) {
			model.put("orderType", OHRT.ORDER.TYPE.BUY);
		} else {
			model.put("orderType", OHRT.ORDER.TYPE.RENT);
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
		model.put("statusName", statusName);
	}

	public static Route getOrderPage = (Request rq, Response rs) -> {

		String orderId = rq.params("orderId");
		Map<String, Object> model = new HashMap<>();
		User loggedUser = RequestUtil.getSessionCurrentUser(rq);
		if (loggedUser != null) {
			List<Order> list = OrderDAO.getOrderListfind(Integer.parseInt(orderId), 0, 0, 0, 0, null);
			Order order = null;
			if (list != null && !list.isEmpty()) {
				order = (Order) list.get(0);
				initOrderMap(model, order);
			}
			model.put("order", order);
			return ViewUtil.render(rq, model, Path.Template.ORDER_ITEM);
		} else {
			rq.session().attribute("redirecturl", "/order/" + orderId);
			rs.redirect(Path.Web.LOGIN);
			return null;
		}
	};

}
