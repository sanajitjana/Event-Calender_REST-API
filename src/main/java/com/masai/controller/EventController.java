package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.EventException;
import com.masai.exception.UserException;
import com.masai.model.Event;
import com.masai.model.EventDTO;
import com.masai.service.EventService;

@RestController
@RequestMapping("/masaicalender")
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping("/create")
	public ResponseEntity<List<Event>> createEvent(@Valid @RequestBody EventDTO eventDTO,
			@RequestParam("userEmail") String userEmail) throws EventException, UserException {
		return new ResponseEntity<List<Event>>(eventService.createEvent(eventDTO, userEmail), HttpStatus.OK);
	}

	@PutMapping("/update/{eventId}")
	public ResponseEntity<Event> updateEvent(@Valid @RequestBody EventDTO eventDTO,
			@PathVariable("eventId") Integer eventId) throws EventException, UserException {
		return new ResponseEntity<Event>(eventService.updateEvent(eventDTO, eventId), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{eventId}")
	public ResponseEntity<Event> deleteEvent(@PathVariable("eventId") Integer eventId) throws EventException, UserException {
		return new ResponseEntity<Event>(eventService.deleteEvent(eventId), HttpStatus.OK);
	}

}
