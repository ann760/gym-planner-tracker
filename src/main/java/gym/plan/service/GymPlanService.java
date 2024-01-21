package gym.plan.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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
		Event event = findOrCreateEvent(eventId);
		
		setFieldsInEvent(event, gymPlanData);
		return new GymPlanData(eventDao.save(event));
	}
		
	private void setFieldsInEvent(Event event, GymPlanData gymPlanData) {
		event.setEventName(gymPlanData.getEventName());
		event.setEventDescription(gymPlanData.getEventDescription());
		
	}

	private Event findOrCreateEvent(Long eventId) {
		Event event;
		
		if(Objects.isNull(eventId)) {
			event = new Event();
		}
		else {
			event = findEventById(eventId);
		}
		return event;
	}

	private Event findEventById(Long eventId) {
		// TODO Auto-generated method stub
		return eventDao.findById(eventId)
			.orElseThrow(() -> new NoSuchElementException(
				"Event with ID=" + eventId + " was not found."));
	}

}
