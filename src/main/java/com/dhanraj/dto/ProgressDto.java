package com.dhanraj.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgressDto {

	
	private Long id;
    private LocalDate date;
    private int completedWorkouts;
    private int caloriesBurned;
    private double weight;
    private Long userId;
    
}
