package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.sql2o.Connection;

import utils.Sql2Object;

public class House extends Property {
	private double yard;

	House(int id, String name, String address, User owner, int bedroom, int bathroom, PropertyStatus status,
			Date registerDate, double utilitiesCost, PurposeType purposeType, int livingroom, int parking, int kitchen,
			String purposeKey, double yard) {
		super(id, name, address, owner, bedroom, bathroom, status, registerDate, utilitiesCost, purposeType, livingroom,
				parking, kitchen, purposeKey);
		this.yard = yard;
	}

	// #region [getter setter]
	public double getYard() {
		return yard;
	}

	public void setYard(double yard) {
		this.yard = yard;
	}
	// #endregion

	@Override
	public int create(Property property, int userId) {
		int parent_id = super.create(property, userId);
		try (Connection con = Sql2Object.open()) {
			String queryString = "INSERT INTO house "
					+ "(ID, Yard) VALUES(:ID, :Yard)";

			con.createQuery(queryString, true)
					.addParameter("ID", parent_id)
					.addParameter("Yard", this.getYard())
					.executeUpdate();
			
			return property.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parent_id;
	}
}
