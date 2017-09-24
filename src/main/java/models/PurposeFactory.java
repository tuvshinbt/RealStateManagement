package models;

public class PurposeFactory {

	public static PurposeType getSellPurpose(double sellPrice) {
		return new SellPurpose(sellPrice);
	}

	public static PurposeType getRentPurpose(double rentMonth, double perMonthPrice, double deposit) {
		return new RentPurpose(rentMonth, perMonthPrice, deposit);
	}
}
