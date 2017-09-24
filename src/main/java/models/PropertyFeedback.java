package models;

import java.util.Date;

public class PropertyFeedback {
	private int id;
	private User account;
	private String comment;
	private Property property;
	private Date registerDate;
	private int status;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	// #endregion
}
