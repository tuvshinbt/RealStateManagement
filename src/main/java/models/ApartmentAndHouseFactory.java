package models;

import java.util.Date;

import constants.OHRT;
import models.dao.PropertyStatusDAO;

public class ApartmentAndHouseFactory {
	private ApartmentAndHouseFactory() {
	}

	/**
	 * Create SELL apartmen
	 * 
	 * @param id
	 * @param name
	 * @param address
	 * @param owner
	 * @param bedroom
	 * @param bathroom
	 * @param livingroom
	 * @param parking
	 * @param kitchen
	 * @param utilitiesCost
	 * @param floor
	 * @param sellPrice
	 * @return
	 */
	public static Property createApartmentSell(int id, String name, String address, User owner, int bedroom,
			int bathroom, int status, int livingroom, int parking, int kitchen, double utilitiesCost, int floor, double sellPrice) {
		Property property = new Apartment(id, 
				name, 
				address, 
				owner, 
				bedroom, 
				bathroom, 
				PropertyStatusDAO.findById(status), 
				new Date(),
				utilitiesCost, 
				PurposeFactory.getSellPurpose(sellPrice), 
				livingroom, 
				parking, 
				kitchen, 
				OHRT.PROPERTY.PURPOSE_TYPE.SELL, 
				floor);
		return property;
	}

	/**
	 * Create RENT apartment
	 * 
	 * @param id
	 * @param name
	 * @param address
	 * @param owner
	 * @param bedroom
	 * @param bathroom
	 * @param livingroom
	 * @param parking
	 * @param kitchen
	 * @param utilitiesCost
	 * @param floor
	 * @param rentMonth
	 * @param perMonthPrice
	 * @param deposit
	 * @return
	 */
	public static Property createApartmentRent(int id, String name, String address, User owner, int bedroom,
			int bathroom, int status, int livingroom, int parking, int kitchen, double utilitiesCost, int floor, double rentMonth, double perMonthPrice, double deposit) {
		Property property = new Apartment(id, 
				name, 
				address, 
				owner, 
				bedroom, 
				bathroom, 
				PropertyStatusDAO.findById(status), 
				new Date(),
				utilitiesCost, 
				PurposeFactory.getRentPurpose(rentMonth, perMonthPrice, deposit), 
				livingroom, 
				parking, 
				kitchen, 
				OHRT.PROPERTY.PURPOSE_TYPE.RENT, 
				floor);
		return property;
	}
	

	
	/**
	 * Create SELL house
	 * 
	 * @param id
	 * @param name
	 * @param address
	 * @param owner
	 * @param bedroom
	 * @param bathroom
	 * @param livingroom
	 * @param parking
	 * @param kitchen
	 * @param utilitiesCost
	 * @param yard
	 * @param sellPrice
	 * @return
	 */
	public static Property createHouseSell(int id, String name, String address, User owner, int bedroom,
			int bathroom, int status, int livingroom, int parking, int kitchen, double utilitiesCost, double yard, double sellPrice) {
		Property property = new House(id, 
				name, 
				address, 
				owner, 
				bedroom, 
				bathroom, 
				PropertyStatusDAO.findById(status), 
				new Date(),
				utilitiesCost, 
				PurposeFactory.getSellPurpose(sellPrice), 
				livingroom, 
				parking, 
				kitchen, 
				OHRT.PROPERTY.PURPOSE_TYPE.SELL, 
				yard);
		return property;
	}

	/**
	 * Create RENT house
	 * @param id
	 * @param name
	 * @param address
	 * @param owner
	 * @param bedroom
	 * @param bathroom
	 * @param livingroom
	 * @param parking
	 * @param kitchen
	 * @param utilitiesCost
	 * @param yard
	 * @param rentMonth
	 * @param perMonthPrice
	 * @param deposit
	 * @return
	 */
	public static Property createHouseRent(int id, String name, String address, User owner, int bedroom,
			int bathroom, int status, int livingroom, int parking, int kitchen, double utilitiesCost, double yard, double rentMonth, double perMonthPrice, double deposit) {
		Property property = new House(id, 
				name, 
				address, 
				owner, 
				bedroom, 
				bathroom, 
				PropertyStatusDAO.findById(status), 
				new Date(),
				utilitiesCost, 
				PurposeFactory.getRentPurpose(rentMonth, perMonthPrice, deposit), 
				livingroom, 
				parking, 
				kitchen, 
				OHRT.PROPERTY.PURPOSE_TYPE.RENT, 
				yard);
		return property;
	}

}
