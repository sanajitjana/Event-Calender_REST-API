package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EventException;
import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.model.UserUpdateDTO;
import com.masai.repository.EventRepo;
import com.masai.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EventRepo eventRepo;

	// get user by email
	public User getuser(String userEmail) throws UserException {

		User existsingUser = userRepo.findByEmail(userEmail);
		if (existsingUser == null)
			throw new UserException("User not found with email : " + userEmail);
		
		return existsingUser;
	}

	@Override
	public User registerUser(UserDTO userDTO) throws UserException {

		User existsingUser = userRepo.findByEmail(userDTO.getEmail());
		if (existsingUser != null)
			throw new UserException("User already exists with email : " + userDTO.getEmail());

		User newUser = new User();
		newUser.setEmail(userDTO.getEmail());
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setMobileNumber(userDTO.getMobileNumber());
		newUser.setDateOfBirth(userDTO.getDateOfBirth());
		newUser.setPassword(userDTO.getPassword());

		return userRepo.save(newUser);
	}

	@Override
	public List<Event> getEventByType(String type) throws EventException {

		List<Event> eventList = new ArrayList<>();

		LocalDate date = LocalDate.now();

		if (type.equalsIgnoreCase("month")) {
			Integer month = date.getMonthValue();
			eventList = eventRepo.getEventByMonth(month);

		} else if (type.equalsIgnoreCase("week")) {
			Integer week = date.getDayOfWeek().getValue();
			eventList = eventRepo.getEventByWeek(week);

		} else if (type.equalsIgnoreCase("day")) {
			Integer day = date.getDayOfMonth();
			eventList = eventRepo.getEventByDay(day);

		} else
			throw new EventException("Invalid event type");

		if (eventList.isEmpty())
			throw new EventException("No event found!");

		return eventList;
	}

	@Override
	public User updateUser(UserUpdateDTO userUpdateDTO, String userEmail) throws UserException {

		// get user from database by email
		User existingUser = getuser(userEmail);

		existingUser.setEmail(userEmail);
		existingUser.setFirstName(userUpdateDTO.getFirstName());
		existingUser.setLastName(userUpdateDTO.getLastName());
		existingUser.setMobileNumber(userUpdateDTO.getMobileNumber());
		existingUser.setDateOfBirth(userUpdateDTO.getDateOfBirth());
		existingUser.setPassword(userUpdateDTO.getPassword());

		return userRepo.save(existingUser);
	}

}
