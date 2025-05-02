package com.dhanraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhanraj.model.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long>{

	
	List<Badge> findByUserId(Long userId); 
}
