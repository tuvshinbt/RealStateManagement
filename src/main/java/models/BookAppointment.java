package models;

import java.util.Date;

import org.sql2o.Connection;

import utils.Sql2Object;

public class BookAppointment {
	private int id;
	private Property property;
	private User buyer;
	private Date appointmentDate;
	private String appointmentComment;
	private AppointmentStatus appointmentStatus;

	public static boolean requestBookAppointment(String pPropertyID, String pApptDate, String pApptInfo, int pBuyerID){
		boolean isCreated = false;
		String queryString = "INSERT INTO bookappointment "+
								"(`PropertyID`, `BuyerID`, `AppointmentDate`, `AppointmentComment`, `AppointmentStatusID`)"+
								" VALUES (:propertyid, :buyerid, :apptdate, :apptinfo, 1)";
		try {
            Connection conn = Sql2Object.open();
            conn.createQuery(queryString)
                    .addParameter("propertyid", pPropertyID)
                    .addParameter("buyerid", pBuyerID)
                    .addParameter("apptdate", pApptDate)
                    .addParameter("apptinfo", pApptInfo)
                    .executeUpdate();            
            isCreated = true;
        } catch ( Exception e ) {
        	
        }
		return isCreated;
	}
	
	public static boolean approveAppointment(String id) {
		boolean isApproved = false;
		String queryString = "UPDATE BookAppointment SET AppointmentStatusID = 2"
				+ " WHERE ID = :id";
		try {
            Connection conn = Sql2Object.open();
            conn.createQuery(queryString)
                    .addParameter("id", id)
                    .executeUpdate();
            
            isApproved = true;
        } catch ( Exception e ) {
        	e.printStackTrace();
        }
		return isApproved;
	}
	
	// #region [getter setter]
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentComment() {
		return appointmentComment;
	}

	public void setAppointmentComment(String appointmentComment) {
		this.appointmentComment = appointmentComment;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	// #endregion
}
