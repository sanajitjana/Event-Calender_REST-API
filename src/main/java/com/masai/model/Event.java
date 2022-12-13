package com.masai.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Title can't be null")
	private String title;

	@NotNull(message = "Description can't be null")
	private String description;

	@NotNull(message = "startTime can't be null")
	private LocalTime startTime;

	@NotNull(message = "Description can't be null")
	private LocalTime endTime;

	@NotNull(message = "Description can't be null")
	@Future(message = "Start Date can't be past date")
	private LocalDate startDate;

	@NotNull(message = "Description can't be null")
	@Future(message = "End Date can't be past date")
	private LocalDate endDate;

	@NotNull(message = "Description can't be null")
	@Email(message = "Email should be in proper format")
	private String userEmail;
}
