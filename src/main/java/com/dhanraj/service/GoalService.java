package com.dhanraj.service;

import java.util.List;

import com.dhanraj.dto.GoalDto; 

public interface GoalService {

	GoalDto createGoal(GoalDto goalDto);
	
	List<GoalDto> getGoalsByUserId(Long userId);
	
	GoalDto updateGoalStatus(Long goalId, Boolean isAchieved);
	
	GoalDto checkProgress(Long goalId);

	
}
