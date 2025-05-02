package com.dhanraj.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhanraj.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long>{

	List<Workout> findByUserId(Long in);

	List<Workout> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
	
}
