package com.dhanraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.dto.ProgressDto;
import com.dhanraj.service.ProgressService;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

	
	
	@Autowired
    private ProgressService progressService;

    @PostMapping
    public ProgressDto addProgress(@RequestBody ProgressDto progressDto) {
        return progressService.addProgress(progressDto);
    }

    @GetMapping("/user/{userId}")
    public List<ProgressDto> getProgressByUser(@PathVariable Long userId) {
        return progressService.getProgressByUserId(userId);
    }

    @PutMapping("/{id}")
    public ProgressDto updateProgress(@PathVariable Long id, @RequestBody ProgressDto progressDto) {
        return progressService.updateProgress(id, progressDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
    }
    
    
}
