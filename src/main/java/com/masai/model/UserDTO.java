package com.masai.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {

	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private LocalDate dateOfBirth;
	private String password;
}
