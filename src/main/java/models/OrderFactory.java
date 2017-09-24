package models;

import java.util.Date;

public class OrderFactory {

	private OrderFactory() {
	}

	/**
	 * Create Buy order
	 * 
	 * @param id
	 * @param account
	 * @param registerDate
	 * @param property
	 * @param status
	 * @param seller
	 * @param buyPrice
	 * @return
	 */
	public static Order createBuyOrder(int id, User account, Date registerDate, Property property, int status,
			User seller, String comment, double buyPrice) {
		return new BuyOrder(id, account, registerDate, property, status, seller, comment, buyPrice);
	}

	/**
	 * Create Rent order
	 * 
	 * @param id
	 * @param account
	 * @param registerDate
	 * @param property
	 * @param status
	 * @param seller
	 * @param rentMonth
	 * @param perMonthPrice
	 * @param deporit
	 * @param extendable
	 * @return
	 */
	public static Order createRentOrder(int id, User account, Date registerDate, Property property, int status,
			User seller, String comment, int rentMonth, double perMonthPrice, double deposit, boolean extendable) {
		return new RentOrder(id, account, registerDate, property, status, seller, comment, rentMonth, perMonthPrice,
				deposit, extendable);
	}

}
