package models.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.data.Row;

import models.PropertyFeedback;
import utils.Sql2Object;

public class PropertyFeedbackDAO {
	public static List<PropertyFeedback> findActiveByPropertyId(int propertyId) throws Exception {
		String queryString = "SELECT * FROM propertyfeedback WHERE PropertyID = :propertyId AND Status = 1";
		try (Connection conn = Sql2Object.open()) {
			List<Row> list = conn.createQuery(queryString).addParameter("propertyId", propertyId).executeAndFetchTable()
					.rows();
			if (list == null || list.isEmpty()) {
				return null;
			} else {
				List<PropertyFeedback> resultList = new ArrayList<>();
				for (Row row : list) {
					PropertyFeedback propertyFeedback = new PropertyFeedback();
					propertyFeedback.setAccount(UserDAO.findById(row.getInteger("AccountID")));
					propertyFeedback.setComment(row.getString("Comment"));
					propertyFeedback.setProperty(PropertyDAO.findById(row.getInteger("PropertyID")));
					propertyFeedback.setRegisterDate(row.getDate("RegisterDate"));
					propertyFeedback.setStatus(row.getInteger("Status"));
					resultList.add(propertyFeedback);
				}
				return resultList;
			}
		}
	}

	public static void save(PropertyFeedback feedback) {
		String queryString = "INSERT INTO propertyfeedback (AccountID, `Comment`, PropertyID, RegisterDate, `Status`) VALUES (:accountId, :comment, :propertyId, :registerDate, :status)";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(queryString).addParameter("accountId", feedback.getAccount().getId())
					.addParameter("comment", feedback.getComment())
					.addParameter("propertyId", feedback.getProperty().getId())
					.addParameter("registerDate", feedback.getRegisterDate())
					.addParameter("status", feedback.getStatus()).executeUpdate();
		}
	}
}
