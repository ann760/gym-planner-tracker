package gym.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public GymPlanData insertEvent(
			@RequestBody GymPlanData gymPlanData) {
		log.info("Creating Event {}");
		return gymPlanService.saveEvent(gymPlanData);
	}
}
