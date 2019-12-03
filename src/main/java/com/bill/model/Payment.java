package com.bill.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Payment implements Serializable {

	private static final long serialVersionUID = -8268149466708885693L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Override
	public String toString() {
		return "payment [id=" + id + ", groupname=" + groupname + ", membername=" + membername + ", amount=" + amount
				+ "]";
	}

}
