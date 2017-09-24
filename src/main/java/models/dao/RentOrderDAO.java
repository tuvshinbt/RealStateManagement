package models.dao;

import org.sql2o.Connection;

import utils.Sql2Object;

public class RentOrderDAO {
	public static void save(Integer id, int rentMonth, double perMonthPrice, double deposit, boolean extendable) {
		String sqlQuery = "INSERT INTO rentorder (ID, RentMonth, PerMonthPrice, Deposit, Extendable) "
				+ "VALUES (:id, :rentMonth, :perMonthPrice, :deposit, :extendable)";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("id", id).addParameter("rentMonth", rentMonth)
					.addParameter("perMonthPrice", perMonthPrice).addParameter("deposit", deposit)
					.addParameter("extendable", extendable).executeUpdate();
		}
	}
}
