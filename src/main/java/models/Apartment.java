package models;

import java.util.Date;

import org.sql2o.Connection;

import utils.Sql2Object;

public class Apartment extends Property {
	private int floor;

	Apartment(int id, String name, String address, User owner, int bedroom, int bathroom, PropertyStatus status,
			Date registerDate, double utilitiesCost, PurposeType purposeType, int livingroom, int parking, int kitchen,
			String purposeKey, int floor) {
		super(id, name, address, owner, bedroom, bathroom, status, registerDate, utilitiesCost, purposeType, livingroom,
				parking, kitchen, purposeKey);
		this.floor = floor;
	}

	// #region [getter setter]
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	// #endregion
	
	@Override
	public int create(Property property, int userId) {
		int parent_id = super.create(property, userId);
		try (Connection con = Sql2Object.open()) {
			String queryString = "INSERT INTO apartment "
					+ "(ID, Floor) VALUES(:ID, :Floor)";

			con.createQuery(queryString, true)
					.addParameter("ID", parent_id)
					.addParameter("Floor", this.getFloor())
					.executeUpdate();
			
			return property.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parent_id;
	}
}
