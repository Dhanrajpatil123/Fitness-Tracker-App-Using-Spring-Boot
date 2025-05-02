package com.dhanraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.dto.BadgeDto;
import com.dhanraj.dto.BadgeRequest;
import com.dhanraj.service.BadgeService;

@RestController
@RequestMapping("/api/badges")
@CrossOrigin(origins = "*")
public class BadgeController {

	
	@Autowired
	private BadgeService badgeService;

	
	@PostMapping("/award")
	public ResponseEntity<?> awardBadge(@RequestBody BadgeRequest badgeRequest){
		
		return ResponseEntity.ok(
					badgeService.awardBadge(
							badgeRequest.getName(), 
							badgeRequest.getDescription(), 
							badgeRequest.getUserId())
					);
	}
	
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<BadgeDto>> getBadges(@PathVariable Long userId){
		
		return ResponseEntity.ok(badgeService.getBadgesByUser(userId));
	}
	
	
}
