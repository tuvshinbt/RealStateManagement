package models.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.sql2o.Connection;
import org.sql2o.data.Row;

import models.AppointmentStatus;
import models.BookAppointment;
import models.Property;
import models.User;
import utils.Sql2Object;

public class BookAppointmentDAO {
	public static List<BookAppointment> findByStatus(int statusId) throws Exception {
		try (Connection conn = Sql2Object.open()) {
			String sqlQuery = "SELECT * FROM bookappointment WHERE AppointmentStatusID = :id";
			List<Row> listRow = conn.createQuery(sqlQuery).addParameter("id", statusId).executeAndFetchTable().rows();
			
			if (listRow == null || listRow.size() == 0) {
				return null;
			} else {
				List<BookAppointment> resultList = new ArrayList<>();
				for (Row row : listRow) {
					BookAppointment appointment = null;
					DateFormat datef = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
					Date date = datef.parse(row.getString("AppointmentDate"));
					int Id = row.getInteger("ID");
					
					Property property = PropertyDAO.findById(row.getInteger("propertyID"));
					AppointmentStatus status = AppointmentStatusDAO.findById(1);
					User buyer = UserDAO.findById(row.getInteger("BuyerID"));
					
					appointment = new BookAppointment();
					appointment.setAppointmentComment(row.getString("AppointmentComment"));
					appointment.setAppointmentDate(date);
					appointment.setAppointmentStatus(status);
					appointment.setBuyer(buyer);
					appointment.setId(Id);
					appointment.setProperty(property);
					resultList.add(appointment);
				}
				return resultList;
			}
		}
	}
}
