package models.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.data.Row;

import models.AppointmentStatus;
import models.BookAppointment;
import models.Property;
import utils.Sql2Object;

public class ApartmentDAO {

	/**
	 * Select List
	 * 
	 * @param searchBy
	 * @param ownerId
	 * @param statusID
	 * @param agentAccount
	 * @param purposeKey
	 * @param order
	 *            - null, 0, 1
	 * @return
	 */
	public static Row findById(int id) {
		try (Connection conn = Sql2Object.open()) {
			String sqlQuery = "SELECT * FROM apartment WHERE ID = :id";
			List<Row> list = conn.createQuery(sqlQuery).addParameter("id", id).executeAndFetchTable().rows();
			return (list == null || list.isEmpty()) ? null : list.get(0);
		}
	}
}
