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
import gym.plan.controller.model.GymPlanData.DrillData;
import gym.plan.controller.model.GymPlanData.SkillData;
import gym.plan.dao.DrillDao;
import gym.plan.dao.EventDao;
import gym.plan.dao.SkillDao;
import gym.plan.entity.Drill;
import gym.plan.entity.Event;
import gym.plan.entity.Skill;

@Service
public class GymPlanService {
	
	@Autowired 
	private EventDao eventDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private DrillDao drillDao;

//	---------------------------------Events-------------------------------------------
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
				throw new DuplicateKeyException("Event with name " + eventName + " already exists.");
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
	
	private Event findEventByName(String eventName) {
		return eventDao.findByEventName(eventName)
			.orElseThrow(() -> new NoSuchElementException(
				"Event with ID=" + eventName + " was not found."));
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
	
	@Transactional(readOnly = true)
	public GymPlanData retrieveEventByName(String eventName) {
		Event event = findEventByName(eventName);
		return new GymPlanData(event);
	}

	@Transactional(readOnly = false)
	public void deleteEventById(Long eventId) {
		Event event = findEventById(eventId);
		eventDao.delete(event);
	}

//	--------------------------------Skills-----------------------------------------
	@Transactional(readOnly = false)
	public SkillData saveSkill(Long eventId, SkillData skillData) {
		Event event = findEventById(eventId);
		Long skillId = skillData.getSkillId();
		Skill skill = findOrCreateSkill(eventId, skillId, skillData.getSkillName());
		
		setFieldsInSkill(skill, skillData);
		
		skill.setEvent(event);
		event.getSkills().add(skill);
		
		return new SkillData(skillDao.save(skill));
	}

	private void setFieldsInSkill(Skill skill, SkillData skillData) {
		skill.setSkillName(skillData.getSkillName());
		skill.setSkillDescription(skillData.getSkillDescription());
		skill.setSkillLevel(skillData.getSkillLevel());
	}
	
	private Skill findOrCreateSkill(Long eventId, Long skillId, String skillName) {
		Skill skill;
		if(Objects.isNull(skillId)) {
			Optional<Skill> opSkill = skillDao.findBySkillName(skillName);
			if(opSkill.isPresent()) {
				throw new DuplicateKeyException("Skill with name " + skillName + " already exists.");
			}	
			skill = new Skill();
		} 
		else {
			skill = findSkillById(skillId);
		}
		return skill;
	}
	
	private Skill findSkillById(Long skillId) {
		return skillDao.findById(skillId)
				.orElseThrow(() -> new NoSuchElementException(
						"Skill with ID=" + skillId + "does not match"));
	}

	@Transactional(readOnly = true)
	public List<SkillData> retrieveAllSkill() {
		// @formatter:off
		return skillDao.findAll()
				.stream()
				.map(cont -> new SkillData(cont))
				.toList();
		// @formatter:on
	}

	@Transactional(readOnly = true)
	public SkillData retrieveSkillById(Long skillId) {
		Skill skill = findSkillById(skillId);
		return new SkillData(skill);
	}

	@Transactional(readOnly = false)
	public void deleteSkillById(Long skillId) {
		Skill skill = findSkillById(skillId);
		skillDao.delete(skill);
	}
	
//	----------------------------------Drills-------------------------------------
	@Transactional(readOnly = false)
	public DrillData saveDrill(Long skillId, DrillData drillData) {
		Skill skill = findSkillById(skillId);
		Long drillId = drillData.getDrillId();
		Drill drill = findOrCreateDrill(skillId, drillId, drillData.getDrillName());
		
		setFieldsInDrill(drill, drillData);
		
		drill.setDrillId(drillId);;
		skill.getDrills().add(drill);
		
		return new DrillData(drillDao.save(drill));
	}

	private void setFieldsInDrill(Drill drill, DrillData drillData) {
		drill.setDrillName(drillData.getDrillName());
		drill.setDrillDescription(drillData.getDrillDescription());
		drill.setDrillEquipment(drillData.getDrillEquipment());
	}

	private Drill findOrCreateDrill(Long skillId, Long drillId, String drillName) {
		Drill drill;
		if(Objects.isNull(drillId)) {
			Optional<Drill> opDrill = drillDao.findByDrillName(drillName);
			if(opDrill.isPresent()) {
				throw new DuplicateKeyException("Drill with name " + drillName + " already exists.");
			}
			drill = new Drill();
		} 
		else {
			drill = findDrillById(drillId);
		}
		return drill;
	}

	private Drill findDrillById(Long drillId) {
		return drillDao.findById(drillId)
				.orElseThrow(() -> new NoSuchElementException(
						"Drill with ID=" + drillId + "does not match"));
	}

	@Transactional(readOnly = true)
	public List<DrillData> retrieveAllDrill() {
		// @formatter:off
		return drillDao.findAll()
		.stream()
		.map(cont -> new DrillData(cont))
		.toList();
		// @formatter:on
	}

	@Transactional(readOnly = true)
	public DrillData retrieveDrillById(Long drillId) {
		Drill drill = findDrillById(drillId);
		return new DrillData(drill);
	}

	@Transactional(readOnly = false)
	public void deleteDrillById(Long drillId) {
		Drill drill = findDrillById(drillId);
		drillDao.delete(drill);
	}
}
