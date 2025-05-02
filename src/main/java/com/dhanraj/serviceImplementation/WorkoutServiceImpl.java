package com.dhanraj.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dhanraj.dto.WorkoutDto;
import com.dhanraj.exception.ResourceNotFoundException;
import com.dhanraj.model.User;
import com.dhanraj.model.Workout;
import com.dhanraj.repository.UserRepository;
import com.dhanraj.repository.WorkoutRepository;
import com.dhanraj.service.WorkoutService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService{
	
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@Override
	public WorkoutDto addWorkout(WorkoutDto workoutDto) {
		
		Workout workout = mapToEntity(workoutDto);

        // ✅ Fetch the user by ID
        User user = userRepository.findById(workoutDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + workoutDto.getUserId()));

        // ✅ Set the User to Workout entity
        workout.setUser(user);

        Workout savedWorkout = workoutRepository.save(workout);
        return mapToDTO(savedWorkout);
	}

	
	
	
	@Override
	public List<WorkoutDto> getWorkoutsByUserId(Long userId) {
		
		List<WorkoutDto> list = workoutRepository.findByUserId(userId)
									.stream().map(this::mapToDTO).toList();
		
		return list;
	}
	
	

	@Override
	public WorkoutDto getWorkoutById(Long id) {
		
		Workout workout = workoutRepository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("Workout not found with ID: " + id));
		
		return mapToDTO(workout);
	}
	
	

	@Override
	public void deleteWorkout(Long id) {

		Workout workout = workoutRepository.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("\"Workout not found with ID: \" + id"));
		
		workoutRepository.delete(workout);
	}

	
	
	
	
	private WorkoutDto mapToDTO(Workout workout) {
		
//        WorkoutDto dto = new WorkoutDto();
//        
//        dto.setId(w.getId());
//        dto.setExerciseName(w.getExerciseName());
//        dto.setType(w.getType());
//        dto.setSets(w.getSets());
//        dto.setReps(w.getReps());
//        dto.setWeight(w.getWeight());
//        dto.setDuration(w.getDuration());
//        dto.setNotes(w.getNotes());
//        dto.setDate(w.getDate());
//        dto.setUserId(w.getUser().getId());
//        
//        return dto;
		
		WorkoutDto dto = new WorkoutDto();
        dto.setId(workout.getId());
        dto.setExerciseName(workout.getExerciseName());
        dto.setType(workout.getType());
        dto.setSets(workout.getSets());
        dto.setReps(workout.getReps());
        dto.setWeight(workout.getWeight());
        dto.setDuration(workout.getDuration());
        dto.setNotes(workout.getNotes());
        dto.setDate(workout.getDate());
        dto.setUserId(workout.getUser().getId()); // important
        return dto;
    }
	
	
	private Workout mapToEntity(WorkoutDto dto) {
		
//        Workout workout = new Workout();
//        
//        
//        workout.setId(dto.getId());
//        workout.setExerciseName(dto.getExerciseName());
//        workout.setType(dto.getType());
//        workout.setSets(dto.getSets());
//        workout.setReps(dto.getReps());
//        workout.setWeight(dto.getWeight());
//        workout.setDuration(dto.getDuration());
//        workout.setNotes(dto.getNotes());
//        workout.setDate(dto.getDate());
//
//        
//        User user =  userRepository.findById(dto.getId())
//        					.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getId()));
//        
//        workout.setUser(user);
//        
//        return workout;
		
		Workout workout = new Workout();
        workout.setExerciseName(dto.getExerciseName());
        workout.setType(dto.getType());
        workout.setSets(dto.getSets());
        workout.setReps(dto.getReps());
        workout.setWeight(dto.getWeight());
        workout.setDuration(dto.getDuration());
        workout.setNotes(dto.getNotes());
        workout.setDate(dto.getDate());
        // ⚠️ Do NOT set user here — we do that in createWorkout()
        return workout;
    }

	
	
}
