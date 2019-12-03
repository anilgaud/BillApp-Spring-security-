package com.bill.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Group1")
@Component
public class Group implements Serializable {
	
	private static final long serialVersionUID = 648558053234484969L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty(message = "Group name can't be empty")
	private String groupname;
	@NotEmpty(message = "Member name can't be empty")
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

	@Override
	public String toString() {
		return "Group [id=" + id + ", groupname=" + groupname + ", membername=" + membername + "]";
	}

}
