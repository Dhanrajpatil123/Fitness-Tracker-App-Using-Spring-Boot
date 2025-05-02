package com.dhanraj.service;

import java.util.List;

import com.dhanraj.dto.UserDto;
import com.dhanraj.model.User;

public interface UserService {

	String registerUser(UserDto userDto);
	
	String loginUser(String email, String password);
	
	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	boolean isEmailExists(String email);

}
