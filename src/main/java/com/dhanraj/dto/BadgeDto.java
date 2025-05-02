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
public class BadgeDto {

	
	private Long id;
    private String name;
    private String description;
    private LocalDate awardedOn;
    private Long userId;
    
}
