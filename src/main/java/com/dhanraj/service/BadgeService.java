package com.dhanraj.service;

import java.util.List;

import com.dhanraj.dto.BadgeDto;

public interface BadgeService {

	
	 BadgeDto awardBadge(String name, String description, Long userId);
	 
	 List<BadgeDto> getBadgesByUser(Long userId);
	
}
