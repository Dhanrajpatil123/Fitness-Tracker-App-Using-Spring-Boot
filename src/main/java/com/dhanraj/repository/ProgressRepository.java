package com.dhanraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhanraj.model.Progress;
import com.dhanraj.model.User;

public interface ProgressRepository extends JpaRepository<Progress, Long>{

	List<Progress> findByUser(User user);
}
