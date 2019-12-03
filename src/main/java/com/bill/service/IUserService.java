package com.bill.service;

import com.bill.Dto.UserDTO;
import com.bill.model.User;

public interface IUserService {

	public boolean chkLogin(String userName, String passWord);

	public void save(User user);

	public String getEmailByName(String name);
	
	public UserDTO getUserByUsername(String username);

}
