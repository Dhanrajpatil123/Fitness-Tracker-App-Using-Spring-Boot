package com.dhanraj.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	private int age;
	
	private String gender;
	
	private LocalDateTime createdAt = LocalDateTime.now();

}
