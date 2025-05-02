package com.dhanraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.dto.LoginRequest;
import com.dhanraj.dto.UserDto;
import com.dhanraj.model.User;
import com.dhanraj.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
		
		String result = userService.registerUser(userDto);
		
		if(result.equals("User registered successfully")) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(result); 
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
	
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
		
		String userLogin = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
		
		if (userLogin.startsWith("Login successful")) {
			
			return ResponseEntity.status(HttpStatus.OK).body(userLogin);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userLogin);
	}
	
	
	
	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email){
		
		boolean exists = userService.isEmailExists(email);
		
		return ResponseEntity.ok(exists);
	}
	
	
}
