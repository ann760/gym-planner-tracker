package gym.plan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gym.plan.controller.model.GymPlanData;
import gym.plan.controller.model.GymPlanData.DrillData;
import gym.plan.controller.model.GymPlanData.SkillData;
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
	
	@PutMapping("/event/{eventId}")
	public GymPlanData updateEvent(@PathVariable Long eventId, 
			@RequestBody GymPlanData gymPlanData) {
		gymPlanData.setEventId(eventId);
		log.info("Updatting event with ID={}", eventId);
		return gymPlanService.saveEvent(gymPlanData);
	}
	
	@GetMapping("/event")
	public List<GymPlanData> retrieveAllEvents() {
		log.info("Retrieving all Events.");
		return gymPlanService.retrieveAllEvents();
	}
	
	@GetMapping("/event/{eventId}")
	public GymPlanData retrieveEventById(@PathVariable Long eventId) {
		log.info("Retrieving event with ID={}", eventId);
		return gymPlanService.retrieveEventById(eventId);
	}
	
	@DeleteMapping("/event")
	public void deleteAllEvents() {
		log.info("Attempting to delete all Events");
		throw new UnsupportedOperationException("Deleting all Events is not allowed");
	}
	
	@DeleteMapping("/event/{eventId}")
	public Map<String, String> deleteEventById(
			@PathVariable Long eventId){
		log.info("Deleteing event with ID={}", eventId);
		
		gymPlanService.deleteEventById(eventId);
		
		return Map.of("message", "Deleting of Event with ID=" + eventId 
				+ " was successful");
	}
	
//	------------------------------------Skills----------------------------------------
	@PostMapping("/{eventId}/skill")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SkillData insertSkill(@PathVariable Long eventId,
			@RequestBody SkillData skillData) {
		log.info("Creating event skill {}", skillData);
		return gymPlanService.saveSkill(eventId, skillData);
	}
	
	@PutMapping("/skill/{skillId}")
	public SkillData updateSkill(@PathVariable Long skillId, 
			@RequestBody SkillData skillData) {
		skillData.setSkillId(skillId);
		log.info("Updatting skill with ID={}", skillId);
		return gymPlanService.saveSkill(skillId, skillData);
	}
	
	@GetMapping("/skill")
	public List<SkillData> retrieveAllSkills() {
		log.info("Retrieving all Skills.");
		return gymPlanService.retrieveAllSkill();
	}
	
	@GetMapping("/skill/{skillId}")
	public SkillData retrieveSkillById(@PathVariable Long skillId) {
		log.info("Retrieving skill with ID={}", skillId);
		return gymPlanService.retrieveSkillById(skillId);
	}
	
	@DeleteMapping("/skill")
	public void deleteAllSkill() {
		log.info("Attempting to delete all Skills");
		throw new UnsupportedOperationException("Deleting all Skills is not allowed");
	}
	
	@DeleteMapping("/skill/{skillId}")
	public Map<String, String> deleteSkillById(
			@PathVariable Long skillId){
		log.info("Deleteing event with ID={}", skillId);
		
		gymPlanService.deleteSkillById(skillId);
		
		return Map.of("message", "Deleting of Skill with ID=" + skillId 
				+ " was successful");
	}
	
//	------------------Drills--------------------------------------------
	@PostMapping("/{skillId}/drill")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DrillData insertDrill(@PathVariable Long skillId,
			@RequestBody DrillData drillData) {
		log.info("Creating drill{}", drillData);
		return gymPlanService.saveDrill(skillId, drillData);
	}
	
	@PutMapping("/{skillId}/drill/{drillId}")
	public DrillData updateDrill(@PathVariable("skillId") Long skillId, @PathVariable("drillId") Long drillId,
			@RequestBody DrillData drillData) {
		drillData.setDrillId(drillId);
		log.info("Updatting drill with ID={}", drillId);
		return gymPlanService.saveDrill(skillId, drillData);
	}
	
	@GetMapping("/drill")
	public List<DrillData> retrieveAllDrills() {
		log.info("Retrieving all Drills.");
		return gymPlanService.retrieveAllDrill();
	}
	
	@GetMapping("/skill/{skillId}")
	public DrillData retrieveDrillById(@PathVariable Long drillId) {
		log.info("Retrieving Drill with ID={}", drillId);
		return gymPlanService.retrieveDrillById(drillId);
	}
	
	@DeleteMapping("/skill")
	public void deleteAllDrill() {
		log.info("Attempting to delete all Drills");
		throw new UnsupportedOperationException("Deleting all Drills is not allowed");
	}
	
	@DeleteMapping("/drill/{drillId}")
	public Map<String, String> deleteDrillById(
			@PathVariable Long drillId){
		log.info("Deleteing Drill with ID={}", drillId);
		
		gymPlanService.deleteDrillById(drillId);
		
		return Map.of("message", "Deleting of Drill with ID=" + drillId 
				+ " was successful");
	}
}
