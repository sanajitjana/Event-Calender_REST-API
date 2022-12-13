package com.masai.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class EventDTO {

	private String title;
	private String description;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate startDate;
	private LocalDate endDate;
	private String userEmail;
}
