package com.dhanraj.dto;



import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalDto {

	
	 	private Long id;
	    private String goalType;
	    private String description;
	    private Double targetValue;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private Boolean isAchieved;
	    private Long userId;
	
}
