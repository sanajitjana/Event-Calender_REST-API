package com.masai.service;

import java.util.List;

import com.masai.exception.EventException;
import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.EventDTO;

public interface EventService {

	public List<Event> createEvent(EventDTO eventDTO, String userEmail) throws EventException, UserException;

	public Event updateEvent(EventDTO eventDTO, Integer eventId) throws EventException, UserException;

	public Event deleteEvent(Integer eventId) throws EventException, UserException;

}
