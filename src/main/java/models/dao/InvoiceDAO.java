package models.dao;

import java.util.List;

import org.sql2o.Connection;

import models.Invoice;
import utils.Sql2Object;

public class InvoiceDAO {
	public static void save(Invoice invoice) {
		String sqlQuery = "INSERT INTO invoice (OrderID, CreatedDate, Amount, StatusID) "
				+ "VALUES (:orderid, SYSDATE(), :amount, :statusId)";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("orderid", invoice.getOrderId())
					.addParameter("amount", invoice.getAmount()).addParameter("statusId", invoice.getStatusId())
					.executeUpdate();
		}
	}

	public static void update(int invoiceId, int statusId) {
		String sqlQuery = "UPDATE invoice SET StatusID = :statusId WHERE ID = :invoiceId";
		;
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("invoiceId", invoiceId).addParameter("statusId", statusId)
					.executeUpdate();
		}
	}

	public static Invoice findByOrderIdAndStatus(int orderId, int statusId) {
		String queryString = "SELECT * FROM invoice WHERE OrderId = :orderId AND StatusID = :statusId";
		try (Connection con = Sql2Object.open()) {
			List<Invoice> list = con.createQuery(queryString).addParameter("orderId", orderId)
					.addParameter("statusId", statusId).executeAndFetch(Invoice.class);
			return (list == null || list.isEmpty()) ? null : list.get(0);
		}
	}

}
