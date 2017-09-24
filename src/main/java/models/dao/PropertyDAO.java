package models.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.data.Row;

import constants.OHRT;
import models.ApartmentAndHouseFactory;
import models.Property;
import models.PropertyImage;
import models.RentPurpose;
import models.SellPurpose;
import models.User;
import utils.Sql2Object;

public class PropertyDAO {

	/**
	 * Select List
	 * 
	 * @param searchBy
	 * @param ownerId
	 * @param statusID
	 * @param agentAccount
	 * @param purposeKey
	 * @param order
	 *            - null, 0, 1
	 * @return
	 */
	public static List<Row> query(int id, String searchBy, int ownerId, int statusID, int agentAccount,
			String purposeKey, int type, Integer order, Integer offSet, Integer limit) {
		try (Connection conn = Sql2Object.open()) {
			String sqlQuery = "SELECT * FROM property";
			boolean hasWhere = false;
			if (id != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "ID = :id";
			}
			if (searchBy != null && !searchBy.isEmpty()) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "(Name LIKE :searchBy or Address LIKE :searchBy)";
			}
			if (ownerId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "OwnerID = :ownerId";
			}
			if (statusID != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "StatusID = :statusID";
			}
			if (agentAccount != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "AgentAccount = :agentAccount";
			}
			if (purposeKey != null && !purposeKey.isEmpty()) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "PurposeKey = :purposeKey";
			}
			if (type != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				if (type == OHRT.PROPERTY.TYPE.HOUSE) {
					sqlQuery += "ID in (SELECT ID FROM house)";
				} else if (type == OHRT.PROPERTY.TYPE.APARTMENT) {
					sqlQuery += "ID in (SELECT ID FROM apartment)";
				}
			}
			if (order != null) {
				sqlQuery += " ORDER BY RegisterDate " + (order == 0 ? "ASC" : "DESC");
			}
			if (limit != null && limit != 0) {
				sqlQuery += " LIMIT ";
				if (offSet != null) {
					sqlQuery += offSet + " , ";
				}
				sqlQuery += "" + limit;
			}

			// Query
			Query query = conn.createQuery(sqlQuery);
			if (id != 0) {
				query.addParameter("id", id);
			}
			if (searchBy != null && !searchBy.isEmpty()) {
				query.addParameter("searchBy", "%" + searchBy + "%");
			}
			if (ownerId != 0) {
				query.addParameter("ownerId", ownerId);
			}
			if (statusID != 0) {
				query.addParameter("statusID", statusID);
			}
			if (agentAccount != 0) {
				query.addParameter("agentAccount", agentAccount);
			}
			if (purposeKey != null && !purposeKey.isEmpty()) {
				query.addParameter("purposeKey", purposeKey);
			}
			// type above
			return query.executeAndFetchTable().rows();
		}
	}

	/**
	 * Select count
	 * 
	 * @param searchBy
	 * @param ownerId
	 * @param statusID
	 * @param agentAccount
	 * @param purposeKey
	 * @param order
	 *            - null, 0, 1
	 * @return
	 */
	public static Integer count(int id, String searchBy, int ownerId, int statusID, int agentAccount, String purposeKey,
			int type) {
		try (Connection conn = Sql2Object.open()) {
			String sqlQuery = "SELECT count(*) count FROM property";
			boolean hasWhere = false;
			if (id != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "ID = :id";
			}
			if (searchBy != null && !searchBy.isEmpty()) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "(Name LIKE :searchBy or Address LIKE :searchBy)";
			}
			if (ownerId != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "OwnerID = :ownerId";
			}
			if (statusID != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "StatusID = :statusID";
			}
			if (agentAccount != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "AgentAccount = :agentAccount";
			}
			if (purposeKey != null && !purposeKey.isEmpty()) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				sqlQuery += "PurposeKey = :purposeKey";
			}
			if (type != 0) {
				if (!hasWhere) {
					sqlQuery += " WHERE ";
					hasWhere = true;
				} else {
					sqlQuery += " AND ";
				}
				if (type == OHRT.PROPERTY.TYPE.HOUSE) {
					sqlQuery += "ID in (SELECT ID FROM house)";
				} else if (type == OHRT.PROPERTY.TYPE.APARTMENT) {
					sqlQuery += "ID in (SELECT ID FROM apartment)";
				}
			}
			Query query = conn.createQuery(sqlQuery);
			if (id != 0) {
				query.addParameter("id", id);
			}
			if (searchBy != null && !searchBy.isEmpty()) {
				query.addParameter("searchBy", "%" + searchBy + "%");
			}
			if (ownerId != 0) {
				query.addParameter("ownerId", ownerId);
			}
			if (statusID != 0) {
				query.addParameter("statusID", statusID);
			}
			if (agentAccount != 0) {
				query.addParameter("agentAccount", agentAccount);
			}
			if (purposeKey != null && !purposeKey.isEmpty()) {
				query.addParameter("purposeKey", purposeKey);
			}
			return query.executeScalar(Integer.class);
		}
	}

	/**
	 * 
	 * @param searchBy
	 * @param ownerId
	 * @param statusID
	 * @param agentAccount
	 * @param purposeKey
	 * @param order
	 * @param offSet
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public static List<Property> getPropertyList(int id, String searchBy, int ownerId, int statusID, int agentAccount,
			String purposeKey, int type, Integer order, Integer offSet, Integer limit) throws Exception {
		List<Row> propertyListRow = query(id, searchBy, ownerId, statusID, agentAccount, purposeKey, type, order,
				offSet, limit);
		if (propertyListRow == null || propertyListRow.size() == 0) {
			return null;
		} else {
			List<Property> resultList = new ArrayList<>();
			for (Row row : propertyListRow) {
				Property property = null;
				int propertyId = row.getInteger("ID");
				String name = row.getString("Name");
				String address = row.getString("Address");
				User owner = UserDAO.findById(row.getInteger("OwnerID"));
				int bedroom = row.getInteger("Bedroom");
				int bathroom = row.getInteger("Bathroom");
				int status = row.getInteger("StatusID");
				double utilitiesCost = row.getDouble("UtilitiesCost");
				int parking = row.getInteger("Parking");
				int livingroom = row.getInteger("Livingroom");
				int kitchen = row.getInteger("Kitchen");

				Row houseRow = HouseDAO.findById(propertyId);
				if (houseRow != null) {
					// house
					if (row.getString("PurposeKey").equalsIgnoreCase(OHRT.PROPERTY.PURPOSE_TYPE.SELL)) {
						SellPurpose purpose = SellPurposeDAO.findById(row.getInteger("PurposeId"));
						property = ApartmentAndHouseFactory.createHouseSell(propertyId, name, address, owner, bedroom,
								bathroom, status, livingroom, parking, kitchen, utilitiesCost,
								houseRow.getDouble("Yard"), purpose.getSellPrice());
					} else {
						RentPurpose purpose = RentPurposeDAO.findById(row.getInteger("PurposeId"));
						property = ApartmentAndHouseFactory.createHouseRent(propertyId, name, address, owner, bedroom,
								bathroom, status, livingroom, parking, kitchen, utilitiesCost,
								houseRow.getDouble("Yard"), purpose.getRentMonth(), purpose.getPerMonthPrice(),
								purpose.getDeposit());
					}
				} else {
					// apartment
					Row apartmentRow = ApartmentDAO.findById(propertyId);
					if (apartmentRow != null) {
						if (row.getString("PurposeKey").equalsIgnoreCase(OHRT.PROPERTY.PURPOSE_TYPE.SELL)) {
							SellPurpose purpose = SellPurposeDAO.findById(row.getInteger("PurposeId"));
							property = ApartmentAndHouseFactory.createApartmentSell(propertyId, name, address, owner,
									bedroom, bathroom, status, livingroom, parking, kitchen, utilitiesCost,
									apartmentRow.getInteger("Floor"), purpose.getSellPrice());
						} else {
							RentPurpose purpose = RentPurposeDAO.findById(row.getInteger("PurposeId"));
							property = ApartmentAndHouseFactory.createApartmentRent(propertyId, name, address, owner,
									bedroom, bathroom, status, livingroom, parking, kitchen, utilitiesCost,
									apartmentRow.getInteger("Floor"), purpose.getRentMonth(),
									purpose.getPerMonthPrice(), purpose.getDeposit());

						}
					}
				}
				if (property == null) {
					throw new Exception("childRow does NOT exist!");
				}
				property.setApprovedBy(UserDAO.findById(row.getInteger("ApprovedBy")));
				property.setRegisterDate(row.getDate("RegisterDate"));
				property.setApprovedDate(row.getDate("ApprovedDate"));
				if(row.getInteger("AgentAccount") != null)
					property.setAgentAccount(UserDAO.findById(row.getInteger("AgentAccount")));
				
				property.setPurposeKey(row.getString("PurposeKey"));
				List<PropertyImage> propertyImageList = ProperyImageDAO.findByPropertyId(propertyId);
				if (propertyImageList != null && !propertyImageList.isEmpty())
					property.setMainPicturePath(propertyImageList.get(0).getUrl());
				property.setDescription(row.getString("Description") != null ? row.getString("Description") : "");
				property.setGoogleMapPath(row.getString("GoogleMapPath") != null ? row.getString("GoogleMapPath") : "");
				resultList.add(property);
			}
			return resultList;
		}
	}

	public static Property findById(int id) throws Exception {
		List<Property> list = getPropertyList(id, null, 0, 0, 0, null, 0, 0, 0, 0);
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			Property property = list.get(0);
			List<PropertyImage> propertyImageList = ProperyImageDAO.findByPropertyId(property.getId());
			if (propertyImageList != null && !propertyImageList.isEmpty()) {
				List<String> urlList = new ArrayList<>();
				for (PropertyImage propertyImage : propertyImageList) {
					urlList.add(propertyImage.getUrl());
				}
				property.setPicturePathList(urlList);
			}
			return property;
		}
	}
	

	public static void updateStatusById(int propertyId, int statusId) {
		String sqlQuery = "UPDATE property SET StatusID = :statusId WHERE  ID = :propertyId";
		try (Connection conn = Sql2Object.open()) {
			conn.createQuery(sqlQuery).addParameter("statusId", statusId).addParameter("propertyId", propertyId)
					.executeUpdate();
		}
	}
}
