package models.dao;

import java.util.List;

import org.sql2o.Connection;

import models.AppointmentStatus;
import models.User;
import utils.Sql2Object;

public class AppointmentStatusDAO {
	public static AppointmentStatus findById(int id) {
		String queryString = "SELECT * FROM AppointmentStatus WHERE ID = :id";

		try (Connection con = Sql2Object.open()) {
			List<AppointmentStatus> list = con.createQuery(queryString).addParameter("id", id).executeAndFetch(AppointmentStatus.class);
			return (list == null || list.isEmpty()) ? null : list.get(0);
			//return con.createQuery(queryString).addParameter("id", id).executeAndFetch(User.class).get(0);
		}
	}
}
