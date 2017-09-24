package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sql2o.Connection;
import utils.Sql2Object;

public abstract class Property {
	private int id;

	protected String name;
	private String address;
	private User owner;
	private int bedroom;
	private int bathroom;
	private PropertyStatus status;
	private User approvedBy;
	private Date registerDate;
	private Date approvedDate;
	private User agentAccount;
	private double utilitiesCost;
	private PurposeType purposeType;

	// added
	private String mainPicturePath;
	private int livingroom;
	private int parking;
	private int kitchen;
	private String purposeKey;

	// added detail menu
	private String subject;
	private List<String> picturePathList;
	private String description;
	private String googleMapPath;
	protected ArrayList<PropertyImage> images = new ArrayList<PropertyImage>();

	Property(int id, String name, String address, User owner, int bedroom, int bathroom, PropertyStatus status,
			Date registerDate, double utilitiesCost, PurposeType purposeType, int livingroom, int parking, int kitchen,
			String purposeKey) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.status = status;
		this.registerDate = registerDate;
		this.utilitiesCost = utilitiesCost;
		this.purposeType = purposeType;
		this.livingroom = livingroom;
		this.parking = parking;
		this.kitchen = kitchen;
		this.purposeKey = purposeKey;
	}
	
	public String getMainPicturePath() {
		return mainPicturePath;
	}

	public void setMainPicturePath(String mainPicturePath) {
		this.mainPicturePath = mainPicturePath;
	}

	public int getLivingroom() {
		return livingroom;
	}

	public void setLivingroom(int livingroom) {
		this.livingroom = livingroom;
	}

	public int getParking() {
		return parking;
	}

	public void setParking(int parking) {
		this.parking = parking;
	}

	public int getKitchen() {
		return kitchen;
	}

	public void setKitchen(int kitchen) {
		this.kitchen = kitchen;
	}

	public String getPurposeKey() {
		return purposeKey;
	}

	public void setPurposeKey(String purposeKey) {
		this.purposeKey = purposeKey;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getPicturePathList() {
		return picturePathList;
	}

	public void setPicturePathList(List<String> picturePathList) {
		this.picturePathList = picturePathList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGoogleMapPath() {
		return googleMapPath;
	}

	public void setGoogleMapPath(String googleMapPath) {
		this.googleMapPath = googleMapPath;
	}

	// #region [getter setter]
	public PurposeType getPurposeType() {
		return purposeType;
	}

	public void setPurposeType(PurposeType purposeType) {
		this.purposeType = purposeType;
	}

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public PropertyStatus getStatus() {
		return status;
	}

	public void setStatus(PropertyStatus status) {
		this.status = status;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public User getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(User agentAccount) {
		this.agentAccount = agentAccount;
	}

	public double getUtilitiesCost() {
		return utilitiesCost;
	}

	public void setUtilitiesCost(double utilitiesCost) {
		this.utilitiesCost = utilitiesCost;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int pId) {
		this.id = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String pAddress) {
		this.address = pAddress;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User pOwner) {
		this.owner = pOwner;
	}
	// #endregion

	public static boolean approveProperty(String id, int approvedBy) {
		boolean isApproved = false;
		String queryString = "UPDATE property SET StatusID = 2, ApprovedBy = :approvedBy, ApprovedDate = SYSDATE()"
				+ " WHERE ID = :id";
		try {
            Connection conn = Sql2Object.open();
            conn.createQuery(queryString)
                    .addParameter("id", id)
                    .addParameter("approvedBy", approvedBy)
                    .executeUpdate();
            
            isApproved = true;
        } catch ( Exception e ) {
        	e.printStackTrace();
        }
		return isApproved;
	}

	public int create(Property property, int userId) {
		try (Connection con = Sql2Object.open()) {
			String queryString = "INSERT INTO property "
					+ "(Name, Address, OwnerID, Bedroom, Bathroom, StatusID, ApprovedBy, RegisterDate, AgentAccount, UtilitiesCost, "
					+ "PurposeKey, PurposeID, Parking, Livingroom, Kitchen, Description) "
					+ "VALUES(:Name, :Address, :OwnerID, :Bedroom, :Bathroom, :StatusID, :ApprovedBy, :RegisterDate, :AgentAccount, :UtilitiesCost,"
					+ ":PurposeKey, :PurposeID, :Parking, :Livingroom, :Kitchen, :Description)";
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();

			long property_id = (long)con.createQuery(queryString, true)
					.addParameter("Name", property.getName())
					.addParameter("Address", property.getAddress())
					.addParameter("OwnerID", userId)
					.addParameter("Bedroom", property.getBedroom())
					.addParameter("Bathroom", property.getBathroom())
					.addParameter("StatusID", property.getStatus().getId())
					.addParameter("ApprovedBy", 0)
					.addParameter("RegisterDate", dtf.format(localDate))
					.addParameter("AgentAccount", property.getAgentAccount().getId())
					.addParameter("UtilitiesCost", property.getUtilitiesCost())
					.addParameter("PurposeKey", property.getPurposeKey())
					.addParameter("PurposeID", property.getPurposeType().getId())
					.addParameter("Parking", property.getParking())
					.addParameter("Livingroom", property.getLivingroom())
					.addParameter("Kitchen", property.getKitchen())
					.addParameter("Description", property.getDescription())
					.executeUpdate()
					.getKey();
			
			property.setId((int)property_id);
			return property.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;	
	}
	
	public void addImage(PropertyImage image) {
		this.images.add(image);
	}

	public ArrayList<PropertyImage> getImage() {
		return this.images;
	}
}
