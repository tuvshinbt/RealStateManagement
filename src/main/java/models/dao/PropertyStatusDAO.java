package models.dao;

import org.sql2o.Connection;

import models.PropertyStatus;
import utils.Sql2Object;

public class PropertyStatusDAO {

	public static PropertyStatus findById(int id) {
		String queryString = "SELECT * FROM propertystatus WHERE ID = :id";

		try (Connection con = Sql2Object.open()) {
			return con.createQuery(queryString).addParameter("id", id).executeAndFetch(PropertyStatus.class).get(0);
		}
	}
}
