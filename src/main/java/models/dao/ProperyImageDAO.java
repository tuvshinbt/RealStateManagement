package models.dao;

import java.util.List;

import org.sql2o.Connection;

import models.PropertyImage;
import utils.Sql2Object;

public class ProperyImageDAO {

	public static List<PropertyImage> findByPropertyId(int propertyId) {
		String queryString = "SELECT * FROM property_image WHERE Property_ID = :propertyId";
		try (Connection conn = Sql2Object.open()) {
			List<PropertyImage> list = conn.createQuery(queryString).addParameter("propertyId", propertyId)
					.executeAndFetch(PropertyImage.class);
			return list;
		}
	}

}
