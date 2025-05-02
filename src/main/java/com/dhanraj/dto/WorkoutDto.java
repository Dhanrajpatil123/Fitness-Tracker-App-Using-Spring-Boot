package com.dhanraj.dto;

import java.time.LocalDate;

import lombok.Data; 

@Data
public class WorkoutDto {

	private Long id;
    private String exerciseName;
    private String type;
    private Integer sets;
    private Integer reps;
    private Double weight;
    private Integer duration;
    private String notes;
    private LocalDate date;
    private Long userId;
}
