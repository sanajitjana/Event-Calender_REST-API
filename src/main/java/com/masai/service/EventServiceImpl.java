package com.masai.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EventException;
import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.EventDTO;
import com.masai.model.User;
import com.masai.repository.EventRepo;
import com.masai.repository.UserRepo;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepo eventRepo;

	@Autowired
	private UserRepo userRepo;

	// get user by email
	public User getuser(String userEmail) throws UserException {

		User existsingUser = userRepo.findByEmail(userEmail);
		if (existsingUser == null)
			throw new UserException("User not found with email : " + userEmail);
		return existsingUser;
	}

	// get user by email
	public Event getEvent(Integer eventId) throws EventException {

		Optional<Event> eventOpt = eventRepo.findById(eventId);
		if (eventOpt.isEmpty())
			throw new EventException("Event not found with id: " + eventId);

		return eventOpt.get();
	}

	@Override
	public List<Event> createEvent(EventDTO eventDTO, String userEmail) throws EventException, UserException {

		User user = getuser(userEmail);

		if (eventDTO == null)
			throw new EventException("Event data can't be null");

		LocalDate eventStartDate = eventDTO.getStartDate();
		long daysBetween = ChronoUnit.DAYS.between(eventDTO.getStartDate(), eventDTO.getEndDate());

		if (daysBetween < 0)
			throw new EventException("Invalid endDate!");

		List<Event> newEventList = new ArrayList<>();

		for (int i = 0; i < daysBetween; i++) {
			Event newEvent = new Event();

			newEvent.setTitle(eventDTO.getTitle());
			newEvent.setDescription(eventDTO.getDescription());
			newEvent.setStartTime(eventDTO.getStartTime());
			newEvent.setEndTime(eventDTO.getEndTime());
			newEvent.setStartDate(eventStartDate.plusDays(i));
			newEvent.setEndDate(eventStartDate.plusDays(i + 1));
			newEvent.setUserEmail(eventDTO.getUserEmail());

			user.getListOfEvents().add(newEvent);
			eventRepo.save(newEvent);
			newEventList.add(newEvent);
		}

		return newEventList;
	}

	@Override
	public Event updateEvent(EventDTO eventDTO, Integer eventId) throws EventException, UserException {

		return null;
	}

	@Override
	public Event deleteEvent(Integer eventId) throws EventException, UserException {

		// get event by id
		Event exixtingEvent = getEvent(eventId);

		String UsereEvent = exixtingEvent.getUserEmail();
		User user = getuser(UsereEvent);

		Event deletedEvent = null;

		List<Event> eventList = user.getListOfEvents();
		for (int i = 0; i < eventList.size(); i++) {
			Event event = eventList.get(i);
			if (event.getId() == eventId) {
				deletedEvent = event;
				eventList.remove(i);
			}
		}

		user.setListOfEvents(eventList);
		userRepo.save(user);
		return deletedEvent;
	}

}
