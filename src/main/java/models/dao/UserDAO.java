package models.dao;

import java.util.List;

import org.sql2o.Connection;

import models.User;
import utils.Sql2Object;

public class UserDAO {

	public static User findById(int id) {
		String queryString = "SELECT * FROM user WHERE ID = :id";

		try (Connection con = Sql2Object.open()) {
			List<User> list = con.createQuery(queryString).addParameter("id", id).executeAndFetch(User.class);
			return (list == null || list.isEmpty()) ? null : list.get(0);
			//return con.createQuery(queryString).addParameter("id", id).executeAndFetch(User.class).get(0);
		}
	}

}
