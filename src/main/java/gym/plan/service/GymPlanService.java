package gym.plan.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gym.plan.controller.model.GymPlanData;
import gym.plan.dao.EventDao;
import gym.plan.entity.Event;

@Service
public class GymPlanService {
	
	@Autowired EventDao eventDao;

	@Transactional(readOnly = false)
	public GymPlanData saveEvent(GymPlanData gymPlanData) {
		Long eventId = gymPlanData.getEventId();
		Event event = findOrCreateEvent(eventId, gymPlanData.getEventName());
		
		setFieldsInEvent(event, gymPlanData);
		return new GymPlanData(eventDao.save(event));
	}
		
	private void setFieldsInEvent(Event event, GymPlanData gymPlanData) {
		event.setEventName(gymPlanData.getEventName());
		event.setEventDescription(gymPlanData.getEventDescription());
		
	}

	private Event findOrCreateEvent(Long eventId, String eventName) {
		Event event;
		
		if(Objects.isNull(eventId)) {
			Optional<Event> opEvent = eventDao.findByEventName(eventName);
			
			if(opEvent.isPresent()) {
				throw new DuplicateKeyException("Event with name " + eventName + 
						" already exists.");
			}
			
			event = new Event();
		}
		else {
			event = findEventById(eventId);
		}
		return event;
	}

	private Event findEventById(Long eventId) {
		return eventDao.findById(eventId)
			.orElseThrow(() -> new NoSuchElementException(
				"Event with ID=" + eventId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<GymPlanData> retrieveAllEvents() {
		// @formatter:off
		return eventDao.findAll()
			.stream()
			.map(cont -> new GymPlanData(cont))
			.toList();
		// @formatter:on
	}

	@Transactional(readOnly = true)
	public GymPlanData retrieveEventById(Long eventId) {
		Event event = findEventById(eventId);
		return new GymPlanData(event);
	}

	@Transactional(readOnly = false)
	public void deleteEventById(Long eventId) {
		Event event = findEventById(eventId);
		eventDao.delete(event);
		
	}
	

}
