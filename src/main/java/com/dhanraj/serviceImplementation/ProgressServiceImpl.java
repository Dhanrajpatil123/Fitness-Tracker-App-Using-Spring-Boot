package com.dhanraj.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanraj.dto.ProgressDto;
import com.dhanraj.exception.ResourceNotFoundException;
import com.dhanraj.model.Progress;
import com.dhanraj.model.User;
import com.dhanraj.repository.ProgressRepository;
import com.dhanraj.repository.UserRepository;
import com.dhanraj.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService {

	@Autowired
	private ProgressRepository progressRepository;

	@Autowired
	private UserRepository userRepository;

	
	
	@Override
	public ProgressDto addProgress(ProgressDto dto) {
		
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Progress progress = new Progress();
		progress.setDate(dto.getDate());
		progress.setCompletedWorkouts(dto.getCompletedWorkouts());
		progress.setCaloriesBurned(dto.getCaloriesBurned());
		progress.setWeight(dto.getWeight());
		progress.setUser(user);

		Progress saved = progressRepository.save(progress);
		dto.setId(saved.getId());
		return dto;
	}
	
	

	@Override
	public List<ProgressDto> getProgressByUserId(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return progressRepository.findByUser(user).stream().map(p -> {
			ProgressDto dto = new ProgressDto();
			dto.setId(p.getId());
			dto.setUserId(user.getId());
			dto.setDate(p.getDate());
			dto.setCompletedWorkouts(p.getCompletedWorkouts());
			dto.setCaloriesBurned(p.getCaloriesBurned());
			dto.setWeight(p.getWeight());
			return dto;
		}).toList();
	}
	
	

	@Override
	public ProgressDto updateProgress(Long id, ProgressDto dto) {
	    // Retrieve the progress entry from the database by its ID
	    Progress progress = progressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Progress not found"));

		// Update the progress with the new values from the DTO
		progress.setDate(dto.getDate());
		progress.setCaloriesBurned(dto.getCaloriesBurned());
		progress.setCompletedWorkouts(dto.getCompletedWorkouts());
		progress.setWeight(dto.getWeight());

		// Save the updated progress entry to the database
		Progress savedProgress = progressRepository.save(progress);

		// Convert the saved entity back to DTO and return
		ProgressDto updatedDto = new ProgressDto();
		updatedDto.setId(savedProgress.getId());
		updatedDto.setUserId(savedProgress.getUser().getId()); // Assuming you want to return userId as well
		updatedDto.setDate(savedProgress.getDate());
		updatedDto.setCompletedWorkouts(savedProgress.getCompletedWorkouts());
		updatedDto.setCaloriesBurned(savedProgress.getCaloriesBurned());
		updatedDto.setWeight(savedProgress.getWeight());

		return updatedDto;
	}


	@Override
	public void deleteProgress(Long id) {
		Progress progress = progressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Progress not found"));
		progressRepository.delete(progress);
	}

}
