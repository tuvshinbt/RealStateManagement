package models.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.data.Row;

import constants.OHRT;
import models.BuyOrder;
import models.Order;
import models.OrderFactory;
import models.RentOrder;
import utils.Sql2Object;

public class OrderDAO {

	/**
	 * Select row
	 * 
	 * @param id
	 * @param accountId
	 * @param propertyId
	 * @param statusId
	 * @param sellerId
	 * @param type
	 * @return
	 */
	public static List<Row> find(int id, int accountId, int propertyId, int statusId, int sellerId, String type) {
		String sqlQuery = "SELECT o.ID, o.AccountID, o.RegisterDate, o.PropertyID, o.StatusID, o.SellerID, o.Comment, ro.RentMonth, ro.PerMonthPrice, ro.Deposit, ro.Extendable, bo.BuyPrice FROM `order` o LEFT JOIN rentorder ro ON o.ID = ro.ID LEFT JOIN buyorder bo ON o.ID = bo.ID";
		try (Connection conn = Sql2Object.open()) {
			boolean hasWhere = false;
			// id
			if (id != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.ID = :id";
			}
			// accountId
			if (accountId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.AccountID = :accountId";
			}
			// propertyId
			if (propertyId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.PropertyID = :propertyId";
			}
			// statusId
			if (statusId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.StatusID = :statusId";
			}
			// sellerId
			if (sellerId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.SellerID = :sellerId";
			}
			// type
			if (type != null) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				if (type.equals(OHRT.ORDER.TYPE.BUY)) {
					sqlQuery += "ro.RentMonth is null";
				} else {
					sqlQuery += "bo.BuyPrice is null";
				}
			}
			// Query
			Query query = conn.createQuery(sqlQuery);
			// id
			if (id != 0) {
				query.addParameter("id", id);
			}
			// accountId
			if (accountId != 0) {
				query.addParameter("accountId", accountId);
			}
			// propertyId
			if (propertyId != 0) {
				query.addParameter("propertyId", propertyId);
			}
			// statusId
			if (statusId != 0) {
				query.addParameter("statusId", statusId);
			}
			// sellerId
			if (sellerId != 0) {
				query.addParameter("sellerId", sellerId);
			}
			return query.executeAndFetchTable().rows();
		}
	}

	/**
	 * SELECT COUNT
	 * 
	 * @param id
	 * @param accountId
	 * @param propertyId
	 * @param statusId
	 * @param sellerId
	 * @param type
	 * @return
	 */
	public static Integer count(int id, int accountId, int propertyId, int statusId, int sellerId, String type) {
		String sqlQuery = "SELECT COUNT(o.id) count FROM `order` o LEFT JOIN rentorder ro ON o.ID = ro.ID LEFT JOIN buyorder bo ON o.ID = bo.ID;";
		try (Connection conn = Sql2Object.open()) {
			boolean hasWhere = false;
			// id
			if (id != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.ID = :id";
			}
			// accountId
			if (accountId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.AccountID = :accountId";
			}
			// propertyId
			if (propertyId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.PropertyID = :propertyId";
			}
			// statusId
			if (statusId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.StatusID = :statusId";
			}
			// sellerId
			if (sellerId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "o.SellerID = :sellerId";
			}
			// type
			if (type != null) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				if (type.equals(OHRT.ORDER.TYPE.BUY)) {
					sqlQuery += "ro.RentMonth is null";
				} else {
					sqlQuery += "bo.BuyPrice is null";
				}
			}
			// Query
			Query query = conn.createQuery(sqlQuery);
			// id
			if (id != 0) {
				query.addParameter("id", id);
			}
			// accountId
			if (accountId != 0) {
				query.addParameter("accountId", accountId);
			}
			// propertyId
			if (propertyId != 0) {
				query.addParameter("propertyId", propertyId);
			}
			// statusId
			if (statusId != 0) {
				query.addParameter("statusId", statusId);
			}
			// sellerId
			if (sellerId != 0) {
				query.addParameter("sellerId", sellerId);
			}
			return query.executeScalar(Integer.class);
		}
	}

	public static List<Order> getOrderListfind(int id, int accountId, int propertyId, int statusId, int sellerId,
			String type) throws Exception {
		List<Row> rowList = find(id, accountId, propertyId, statusId, sellerId, type);
		if (rowList == null || rowList.isEmpty()) {
			return null;
		} else {
			List<Order> orderList = new ArrayList<>();
			for (Row row : rowList) {
				Order order = null;
				if (row.getDouble("RentMonth") != null) {
					// rent
					order = OrderFactory.createRentOrder(row.getInteger("ID"),
							UserDAO.findById(row.getInteger("AccountID")), row.getDate("RegisterDate"),
							PropertyDAO.findById((Integer) row.getInteger("PropertyID")), row.getInteger("StatusID"),
							UserDAO.findById((Integer) row.getInteger("SellerID")), row.getString("Comment"),
							row.getInteger("RentMonth"), row.getDouble("PerMonthPrice"), row.getDouble("Deposit"),
							Boolean.parseBoolean(row.getString("Extendable")));
				} else {
					// buy
					order = OrderFactory.createBuyOrder(row.getInteger("ID"),
							UserDAO.findById(row.getInteger("AccountID")), row.getDate("RegisterDate"),
							PropertyDAO.findById(row.getInteger("PropertyID")), row.getInteger("StatusID"),
							UserDAO.findById(row.getInteger("SellerID")), row.getString("Comment"),
							row.getDouble("BuyPrice"));
				}
				orderList.add(order);
			}
			return orderList;
		}
	}

	public static int save(Order order) {
		String sqlQuery = "INSERT INTO `order` (AccountID, RegisterDate, PropertyID, StatusID, SellerID, `Comment`) "
				+ "VALUES (:accountId, SYSDATE(), :propertyId, 1, :sellerId, :comment)";
		try (Connection conn = Sql2Object.open()) {
			long id = (long) conn.createQuery(sqlQuery).addParameter("accountId", order.getAccount().getId())
					.addParameter("propertyId", order.getProperty().getId())
					.addParameter("sellerId", order.getSeller().getId()).addParameter("comment", order.getComment())
					.executeUpdate().getKey();
			if (order instanceof BuyOrder) {
				BuyOrderDAO.save((int) id, ((BuyOrder) order).getBuyPrice());
			} else if (order instanceof RentOrder) {
				RentOrderDAO.save((int) id, ((RentOrder) order).getRentMonth(), ((RentOrder) order).getPerMonthPrice(),
						((RentOrder) order).getDeposit(), ((RentOrder) order).isExtendable());
			}
			return (int) id;
		}
	}

	public static void updateStatusById(int orderId, int statusId) {
		String sqlQuery = "UPDATE `order` SET StatusID = :statusId WHERE  ID = :orderId";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("statusId", statusId).addParameter("orderId", orderId)
					.executeUpdate();
		}
	}

}
