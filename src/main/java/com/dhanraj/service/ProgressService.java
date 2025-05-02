package com.dhanraj.service;

import java.util.List;

import com.dhanraj.dto.ProgressDto;

public interface ProgressService {

	
	 	ProgressDto addProgress(ProgressDto progressDto);
	    
	 	List<ProgressDto> getProgressByUserId(Long userId);
	    
	 	ProgressDto updateProgress(Long id, ProgressDto progressDto);
	    
	 	void deleteProgress(Long id);
	    
}
