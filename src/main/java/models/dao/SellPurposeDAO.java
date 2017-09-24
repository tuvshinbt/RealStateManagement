package models.dao;

import java.util.List;

import org.sql2o.Connection;

import models.SellPurpose;
import utils.Sql2Object;

public class SellPurposeDAO {

	public static SellPurpose findById(int id) {
		String queryString = "SELECT *FROM sellpurpose WHERE ID = :id";

		try (Connection con = Sql2Object.open()) {
			List<SellPurpose> list = con.createQuery(queryString).addParameter("id", id)
					.executeAndFetch(SellPurpose.class);
			return (list == null || list.isEmpty()) ? null : list.get(0);
		}
	}
}
