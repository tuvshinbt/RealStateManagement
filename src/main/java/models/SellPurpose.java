package models;

import org.sql2o.Connection;

import utils.Sql2Object;

public class SellPurpose implements PurposeType {
	private int id;
	private double sellPrice;

	SellPurpose() {
	}

	SellPurpose(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public double calculatePrice() {
		return sellPrice;
	}
	// #region [getter setter]

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int create() {
		int purpose_id = 0;
		String queryString = "INSERT INTO sellpurpose (SellPrice) values(:sellPrice)";

        try {
            Connection conn = Sql2Object.open();
            long id = (long)conn.createQuery(queryString)
            	.addParameter("sellPrice", sellPrice)
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

	// #endregion
}
