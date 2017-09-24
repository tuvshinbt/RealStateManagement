package models;

import org.sql2o.Connection;

import utils.Sql2Object;

public class PropertyImage {
	private int id;
	private int properyId;
	private String url;

	public PropertyImage(String url) {
		this.url = url;
	}
	
	public PropertyImage(int id, int properyId, String url) {
		this.id = id;
		this.properyId = properyId;
		this.url = url;
	}

	public boolean create(int property_id) {
		boolean isCreated = false;
		try (Connection con = Sql2Object.open()) {
			con.createQuery("INSERT INTO property_image(Property_ID, Url) VALUES (:property_id, :url)")
					.addParameter("property_id", property_id).addParameter("url", url).executeUpdate();
			isCreated = true;
		} catch (Exception e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		return isCreated;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public int getProperty_ID() {
		return properyId;
	}

	public void setProperty_ID(int properyId) {
		this.properyId = properyId;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

}
