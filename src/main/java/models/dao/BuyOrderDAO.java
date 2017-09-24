package models.dao;

import org.sql2o.Connection;

import utils.Sql2Object;

public class BuyOrderDAO {
	public static void save(Integer id, double buyPrice) {
		String sqlQuery = "INSERT INTO buyorder (ID, BuyPrice) " + "VALUES (:id, :buyPrice)";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("id", id).addParameter("buyPrice", buyPrice).executeUpdate();
		}
	}
}
