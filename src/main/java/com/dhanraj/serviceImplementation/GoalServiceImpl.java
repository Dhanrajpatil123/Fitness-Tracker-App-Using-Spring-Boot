package com.dhanraj.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanraj.dto.GoalDto;
import com.dhanraj.exception.ResourceNotFoundException;
import com.dhanraj.model.Goal;
import com.dhanraj.model.User;
import com.dhanraj.model.Workout;
import com.dhanraj.repository.GoalRepository;
import com.dhanraj.repository.UserRepository;
import com.dhanraj.repository.WorkoutRepository;
import com.dhanraj.service.GoalService;

@Service
public class GoalServiceImpl implements GoalService{
	
	
	@Autowired
	private GoalRepository goalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	
	

	@Override
	public GoalDto createGoal(GoalDto goalDto) {
		
		Goal goal = mapToEntity(goalDto);
		
		User user = userRepository.findById(goalDto.getUserId())
						.orElseThrow(() -> new ResourceNotFoundException("User Not Found Exception"));
		
		goal.setUser(user);
		Goal saved = goalRepository.save(goal);
		
		return mapToDto(saved);
	}
	
	
	

	@Override
	public List<GoalDto> getGoalsByUserId(Long userId) {
		
		List<GoalDto> goalList = goalRepository.findByUserId(userId)
							.stream()
							.map(this::mapToDto)
							.toList();
		return goalList;
	}
	
	

	@Override
	public GoalDto updateGoalStatus(Long goalId, Boolean isAchieved) {
		
		Goal goal = goalRepository.findById(goalId)
				.orElseThrow(() -> new ResourceNotFoundException("Goal Not Found"));
		
		goal.setIsAchieved(isAchieved);
		
		Goal updated = goalRepository.save(goal);
		
		return mapToDto(updated);
	}
	
	
	
	@Override
	public GoalDto checkProgress(Long goalId) {
		
		Goal goal = goalRepository.findById(goalId)
						.orElseThrow(() -> new ResourceNotFoundException("Goal Not Found"));
		
		Long userId = goal.getUser().getId();
		
		List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(
	            userId, goal.getStartDate(), goal.getEndDate());
		
		
		double progressValue = 0;
		
		switch (goal.getGoalType()) {
		case "workoutsPerWeek": {
				progressValue = workouts.size();  // count workout
			break;
		}
		case "targetWeight": {
			progressValue = workouts.stream()
								.filter(w -> w.getWeight() != null)
								.mapToDouble(Workout::getWeight)
								.max().orElse(0);
			break;
		}
		
		case "runDistance" :{
			progressValue = workouts.stream()
								.mapToDouble(w -> w.getDuration() != null ? w.getDuration() : 0)
								.sum();
			break;
		}
		default:
			throw new  RuntimeException("Unknown Goal Type");
		}
		
		
		boolean achieved = progressValue >= goal.getTargetValue();
		
		goal.setIsAchieved(achieved);
		
		goalRepository.save(goal);
		
		return mapToDto(goal);
	}
	

	
	
	
	
	private GoalDto mapToDto(Goal goal) {
		
		return GoalDto.builder()
				.id(goal.getId())
				.goalType(goal.getGoalType())
				.description(goal.getDescription())
                .targetValue(goal.getTargetValue())
                .startDate(goal.getStartDate())
                .endDate(goal.getEndDate())
                .isAchieved(goal.getIsAchieved())
                .userId(goal.getUser().getId())
                .build();
	}
	
	
	
	private Goal mapToEntity(GoalDto dto) {
		
		return Goal.builder()
                .goalType(dto.getGoalType())
                .description(dto.getDescription())
                .targetValue(dto.getTargetValue())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isAchieved(dto.getIsAchieved() != null ? dto.getIsAchieved() : false)
                .build();
	}





	
	 
	
}
