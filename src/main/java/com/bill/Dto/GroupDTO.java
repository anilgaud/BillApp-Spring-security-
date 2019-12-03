package com.bill.Dto;

import java.io.Serializable;

public class GroupDTO implements Serializable {

	private static final long serialVersionUID = -69998817133352954L;

	private long id;

	private String groupname;

	private String membername;

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

}
