package com.masai.service;

import java.util.List;

import com.masai.exception.EventException;
import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.model.UserUpdateDTO;

public interface UserService {

	public User registerUser(UserDTO userDTO) throws UserException;

	public List<Event> getEventByType(String type) throws EventException;

	public User updateUser(UserUpdateDTO userUpdateDTO, String userEmail) throws UserException;
}
