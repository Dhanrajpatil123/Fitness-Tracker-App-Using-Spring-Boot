package com.dhanraj.serviceImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanraj.dto.BadgeDto;
import com.dhanraj.model.Badge;
import com.dhanraj.model.User;
import com.dhanraj.repository.BadgeRepository;
import com.dhanraj.repository.UserRepository;
import com.dhanraj.service.BadgeService;

@Service
public class BadgeServiceImpl implements BadgeService{

	
	
	@Autowired
	private BadgeRepository badgeRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public BadgeDto awardBadge(String name, String description, Long userId) {
		
		User user = userRepository.findById(userId)
									.orElseThrow(() -> new RuntimeException("User Not Found Exception"));
		
		Badge badge = Badge.builder()
							.name(name)
							.description(description)
							.awardedOn(LocalDate.now())
							.user(user)
							.build();
		
		return mapToDto(badgeRepository.save(badge));
	}
	
	
	

	@Override
	public List<BadgeDto> getBadgesByUser(Long userId) {
		
		return badgeRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
	}
	

	
	
	
	
	private BadgeDto mapToDto(Badge badge) {
		
		return BadgeDto.builder()
				.id(badge.getId())
				.name(badge.getName())
                .description(badge.getDescription())
                .awardedOn(badge.getAwardedOn())
                .userId(badge.getUser().getId())
                .build();
	}
	
	
	
	
}
