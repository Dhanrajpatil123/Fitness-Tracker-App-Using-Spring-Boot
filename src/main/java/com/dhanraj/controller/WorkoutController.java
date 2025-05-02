package com.dhanraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.dto.WorkoutDto;
import com.dhanraj.exception.ResourceNotFoundException;
import com.dhanraj.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {

	
	@Autowired
	private WorkoutService workoutService;
	
	
	
	@PostMapping
	public ResponseEntity<?> createWorkout(@RequestBody WorkoutDto workoutDto){
		
		
		if (workoutDto.getUserId() == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID must not be null");
	    }
		
		
		WorkoutDto addWorkout = workoutService.addWorkout(workoutDto);
		
		if (addWorkout != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(addWorkout);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResourceNotFoundException("Something went wrong"));
	}
	
	
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<WorkoutDto>> getWorkoutsByUser(@PathVariable Long userId){
		
		List<WorkoutDto> allUsers = workoutService.getWorkoutsByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkoutDto> getWorkoutById(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(workoutService.getWorkoutById(id));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteWorkoutById(@PathVariable Long id){
		
		if(workoutService.getWorkoutById(id) != null) {
			
			workoutService.deleteWorkout(id);
			return ResponseEntity.ok("Workout deleted successfully");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found");
	}
	
	
	
	
	
}
