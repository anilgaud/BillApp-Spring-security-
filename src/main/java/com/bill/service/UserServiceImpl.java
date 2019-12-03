package com.bill.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.Dto.UserDTO;
import com.bill.model.User;
import com.bill.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean chkLogin(String userName, String passWord) {

		List<User> user = userRepository.findAll();

		for (User u : user) {
			if (u.getUsername().equals(userName) && u.getPassword().equals(passWord))
				return true;
		}
		return false;

	}

	@Override
	public void save(User user) {

		userRepository.save(user);

	}

	@Override
	public String getEmailByName(String name) {

		return userRepository.getEmailByName(name);
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		UserDTO userDTO=new UserDTO();
		User user=userRepository.getUserByUsername(username);
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

}
