package models.dao;

import org.sql2o.Connection;

import models.Payment;
import utils.Sql2Object;

public class PaymentDAO {
	public static void save(Payment payment) {
		String sqlQuery = "INSERT INTO payment (InvoiceID, OrderID, Amount, PaidTotal, PaymentType, CardType, PaidDate) "
				+ "VALUES (:invoiceId, :orderId, :amount, :paidTotal, :paymentType, :cardType, SYSDATE())";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("invoiceId", payment.getInvoiceId())
					.addParameter("orderId", payment.getOrderId()).addParameter("amount", payment.getAmount())
					.addParameter("paidTotal", payment.getPaidTotal())
					.addParameter("paymentType", payment.getPaymentType())
					.addParameter("cardType", payment.getCardType()).executeUpdate();
		}
	}

}
