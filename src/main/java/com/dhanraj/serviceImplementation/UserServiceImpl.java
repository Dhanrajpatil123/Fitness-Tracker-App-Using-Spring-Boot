package com.dhanraj.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanraj.dto.UserDto;
import com.dhanraj.exception.ResourceNotFoundException;
import com.dhanraj.model.User;
import com.dhanraj.repository.UserRepository;
import com.dhanraj.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	 
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public String registerUser(UserDto userDto) {
		
		if (userRepository.existsByEmail(userDto.getEmail())) {
			
			return "Email already exists!";
		}
		
		
		User user = new User();
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAge(userDto.getAge());
		user.setGender(userDto.getGender());
		
		
		userRepository.save(user);
		
		return "User registered successfully";
	}
	
	
	

	@Override
	public List<User> getAllUsers() {
		
		List<User> allUsers = userRepository.findAll();
		
		return allUsers;
	}
	
	

	@Override
	public User getUserById(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id : " + id));
		
		return user;
	}

	


	@Override
	public String loginUser(String email, String password) {
		
		User user = userRepository.findByEmail(email)
				 		.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
		
		if (! user.getPassword().equals(password)) {
			
			return "Invalid Credentials";
		}
		
		
		return "Login successful. Welcome, " + user.getName();
	}





	@Override
	public boolean isEmailExists(String email) {
		
		return userRepository.existsByEmail(email); 
	}

	
	
}
