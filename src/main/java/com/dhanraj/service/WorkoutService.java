package com.dhanraj.service;

import java.util.List;

import com.dhanraj.dto.WorkoutDto;

public interface WorkoutService{

	
	WorkoutDto addWorkout(WorkoutDto workoutDto);
	
	List<WorkoutDto> getWorkoutsByUserId(Long userId);
	
	WorkoutDto getWorkoutById(Long id);
	
	void deleteWorkout(Long id);
	
}
