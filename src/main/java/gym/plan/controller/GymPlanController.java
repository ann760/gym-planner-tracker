package gym.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gym.plan.controller.model.GymPlanData;
import gym.plan.service.GymPlanService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("gym_plan")
@Slf4j
public class GymPlanController {
	
	@Autowired
	private GymPlanService gymPlanService;
	
	@PostMapping("/event")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GymPlanData insertEvent(
			@RequestBody GymPlanData gymPlanData) {
		log.info("Creating Event {}");
		return gymPlanService.saveEvent(gymPlanData);
	}
	
	@GetMapping("/event")
	public List<GymPlanData> retrieveAllEvents() {
		log.info("Retrieving all Events.");
		return gymPlanService.retrieveAllEvents();
	}
	
	@GetMapping("/event/{eventId}")
	public GymPlanData retrieveEventById(@PathVariable Long eventId) {
		log.info("Retrieving event with ID={}", + eventId);
		return gymPlanService.retrieveEventById(eventId);
	}
}
