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
public class Workout {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exerciseName;
    
    private String type;  // strength, cardio, flexibility
    
    private Integer sets;
    
    private Integer reps;
    
    private Double weight;
    
    private Integer duration; // in minutes
    
    private String notes;
    
    private LocalDate date;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
}
