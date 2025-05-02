package com.dhanraj.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goal {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalType; // e.g., "workoutsPerWeek", "targetWeight", "runDistance"

    private String description;

    private Double targetValue;

    private LocalDate startDate;
    
    private LocalDate endDate;

    private Boolean isAchieved = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    
}
