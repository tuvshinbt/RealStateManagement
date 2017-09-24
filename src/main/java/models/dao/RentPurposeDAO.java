package models.dao;

import java.util.List;

import org.sql2o.Connection;

import models.RentPurpose;
import utils.Sql2Object;

public class RentPurposeDAO {

	public static RentPurpose findById(int id) {
		String queryString = "SELECT *FROM rentpurpose WHERE ID = :id";

		try (Connection con = Sql2Object.open()) {
			List<RentPurpose> list = con.createQuery(queryString).addParameter("id", id)
					.executeAndFetch(RentPurpose.class);
			return (list == null || list.isEmpty()) ? null : list.get(0);
		}
	}
}
