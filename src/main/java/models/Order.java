package models;

import java.util.Date;

public abstract class Order {
	private int id;
	private User account;
	private Date registerDate;
	private Property property;
	private int status;
	private User seller;
	private String comment;
	public abstract double calculatePrice();

	Order(int id, User account, Date registerDate, Property property, int status, User seller, String comment) {
		super();
		this.id = id;
		this.account = account;
		this.registerDate = registerDate;
		this.property = property;
		this.status = status;
		this.seller = seller;
		this.comment = comment;
	}

	// #region [getter setter]
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	// #endregion
}
