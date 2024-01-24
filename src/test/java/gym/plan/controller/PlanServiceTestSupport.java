package gym.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import gym.plan.controller.model.GymPlanData;
import gym.plan.entity.Event;

public class PlanServiceTestSupport {
	
	private static final String EVENT_TABLE = "event";
	
		//formatter:off
		private GymPlanData insertEvent1 = new GymPlanData(
			1L, 
			"High Bar", 
			"A 13.5 foot high bar on a steel frame."
				);
		//formatter:on
	
		//formatter:off
		private GymPlanData insertEvent2 = new GymPlanData(
			2L, 
			"Parell Bars", 
			"Two parell bars on a steel frame."
				);
			//formatter:on
	
		private GymPlanData updateEvent1 = new GymPlanData(
			1L, 
			"High Bar Update", 
			"A 13.5 foot high bar on a steel frame updated."
				);
		//formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private GymPlanController gymPlanController;
	
	protected GymPlanData buildInsertEvent(int which) {
		return which == 1 ? insertEvent1 : insertEvent2;
	}
	
	protected int rowsInEventTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, EVENT_TABLE);
	}

	protected GymPlanData insertEvent(GymPlanData gymPlanData) {
		Event event = gymPlanData.toEvent() ;
		GymPlanData clone = new GymPlanData(event);
		
		clone.setEventId(null);
		return gymPlanController.insertEvent(clone);
	}
	
	protected GymPlanData retrieveEventById(Long eventId) {
		return gymPlanController.retrieveEventById(eventId);
	}
	
	protected List<GymPlanData> insertTwoEvents() {
		GymPlanData location1 = insertEvent(buildInsertEvent(1));
		GymPlanData location2 = insertEvent(buildInsertEvent(2));
		return List.of(location1, location2);
	}
	
	protected List<GymPlanData> retrieveAllEvents() {
		return gymPlanController.retrieveAllEvents();
	}

	protected GymPlanData updateEvent(GymPlanData gymPlanData) {
		return gymPlanController.updateEvent(gymPlanData.getEventId(), 
				gymPlanData);
	}

	protected GymPlanData buildUpdateEvent() {
		return updateEvent1;
	}
}
