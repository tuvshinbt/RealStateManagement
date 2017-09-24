package models;

import java.util.Date;

public class BuyOrder extends Order {
	private double buyPrice;

	BuyOrder(int id, User account, Date registerDate, Property property, int status, User seller, String comment,
			double buyPrice) {
		super(id, account, registerDate, property, status, seller, comment);
		this.buyPrice = buyPrice;
	}

	@Override
	public double calculatePrice() {
		return buyPrice;
	}

	// #region [getter setter]
	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	// #endregion
}
