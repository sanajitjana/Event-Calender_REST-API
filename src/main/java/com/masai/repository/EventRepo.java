package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {

	@Query("select e from Event e where month(e.startDate)=?1")
	public List<Event> getEventByMonth(Integer month);

	@Query("select e from Event e where week(e.startDate)=?1")
	public List<Event> getEventByWeek(Integer week);

	@Query("select e from Event e where day(e.startDate)=?1")
	public List<Event> getEventByDay(Integer day);

}
