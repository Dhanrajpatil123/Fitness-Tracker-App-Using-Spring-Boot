package com.dhanraj.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;



@Entity
@Data
public class Progress {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private int completedWorkouts;
    private int caloriesBurned;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    
}
