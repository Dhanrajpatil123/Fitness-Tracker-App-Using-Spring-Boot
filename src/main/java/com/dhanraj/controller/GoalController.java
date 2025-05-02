package com.dhanraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.dto.GoalDto;
import com.dhanraj.exception.ResourceNotFoundException;
import com.dhanraj.service.GoalService;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class GoalController {

	
	@Autowired
	private GoalService goalService;
	
	
	@PostMapping
	public ResponseEntity<?> createGoal(@RequestBody GoalDto goalDto){
		
		GoalDto added = goalService.createGoal(goalDto);
		
		if (added != null) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Goal Added Successfully... \n User Id : " + goalDto.getUserId());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went Wrong...");
	}
	
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<GoalDto>> getGoalsByUserId(@PathVariable Long userId){
		
		List<GoalDto> getGoal = goalService.getGoalsByUserId(userId);
		
		if (! getGoal.isEmpty()) {
			
			return ResponseEntity.ok(getGoal);
		}
		
		throw new ResourceNotFoundException("No goals found for user with ID: " + userId);
	}
	
	
	
	@PutMapping("/{goalId}/status")
	public ResponseEntity<GoalDto> updateGoalStatus(@PathVariable Long goalId, @RequestParam Boolean isArchived){
		
		return ResponseEntity.ok(goalService.updateGoalStatus(goalId, isArchived));
	}
	
	
	
	@GetMapping("/{goalId}/check-progress")
	public ResponseEntity<GoalDto> checkGoalProgress(@PathVariable Long goalId){
		
		return ResponseEntity.ok(goalService.checkProgress(goalId));
	}
	
	
}
