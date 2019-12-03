package com.bill.Dto;

import java.io.Serializable;
import java.util.Set;

import com.bill.model.Role;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -3345118591262388724L;

	private long Id;

	private String username;

	private String password;

	private String name;

	private String email;
	
	private Set<Role> roles;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
