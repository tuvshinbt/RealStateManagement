package models;

import java.util.Date;

public class RentOrder extends Order {
	private int rentMonth;
	private double perMonthPrice;
	private double deposit;
	private boolean extendable;

	RentOrder(int id, User account, Date registerDate, Property property, int status, User seller, String comment,
			int rentMonth, double perMonthPrice, double deposit, boolean extendable) {
		super(id, account, registerDate, property, status, seller, comment);
		this.rentMonth = rentMonth;
		this.perMonthPrice = perMonthPrice;
		this.deposit = deposit;
		this.extendable = extendable;
	}

	@Override
	public double calculatePrice() {
		return deposit + (perMonthPrice * rentMonth);
	}

	// #region [getter setter]
	public int getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(int rentMonth) {
		this.rentMonth = rentMonth;
	}

	public double getPerMonthPrice() {
		return perMonthPrice;
	}

	public void setPerMonthPrice(double perMonthPrice) {
		this.perMonthPrice = perMonthPrice;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public boolean isExtendable() {
		return extendable;
	}

	public void setExtendable(boolean extendable) {
		this.extendable = extendable;
	}
	// #endregion
}
