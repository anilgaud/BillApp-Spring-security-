package com.bill.Dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = -8353629688877687418L;

	private long id;

	private String groupname;

	private String membername;

	private double amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
