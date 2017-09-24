package models;

import org.sql2o.Connection;

import utils.Sql2Object;

public class RentPurpose implements PurposeType {
	private int id;
	private double rentMonth;
	private double perMonthPrice;
	private double deposit;

	RentPurpose() {
	}

	RentPurpose(double rentMonth, double perMonthPrice, double deposit) {
		this.rentMonth = rentMonth;
		this.perMonthPrice = perMonthPrice;
		this.deposit = deposit;
	}

	public double calculatePrice() {
		return perMonthPrice;
	}

	// region [getter setter]
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(double rentMonth) {
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

	public int create() {
		int purpose_id = 0;
		String queryString = "INSERT INTO rentpurpose (RentMonth, PerMonthPrice, Deposit) "
				+ "values(:rentMonth, :perMonthPrice, :deposit)";

        try {
            Connection conn = Sql2Object.open();
            long id = (long)conn.createQuery(queryString)
            	.addParameter("rentMonth", rentMonth)
            	.addParameter("perMonthPrice", perMonthPrice)
            	.addParameter("deposit", deposit)
            	.executeUpdate()
				.getKey();
            purpose_id = (int)id;
        } catch ( Exception e ) {
            System.err.println(e.getClass());
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        return purpose_id;
	}
}
