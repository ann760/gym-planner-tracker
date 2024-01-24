package gym.plan.controller.model;

import java.util.HashSet;
import java.util.Set;

import gym.plan.entity.Drill;
import gym.plan.entity.Event;
import gym.plan.entity.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GymPlanData {
	private Long eventId;
	private String eventName;
	private String eventDescription;
	private Set<SkillData> skills = new HashSet<>();
	
	public GymPlanData(Event event) {
		this.eventId = event.getEventId();
		this.eventName = event.getEventName();
		this.eventDescription = event.getEventDescription();
		
		for(Skill skill : event.getSkills()) {
			this.skills.add(new SkillData(skill));
		}
	}
	
	public GymPlanData(Long eventId, String eventName,
			  String eventDescription) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
	}
		
	public Event toEvent() {
		Event event = new Event();
		event.setEventId(eventId);
		event.setEventName(eventName);
		event.setEventDescription(eventDescription);
			
		for(SkillData skillData : skills) {
			event.getSkills().add(skillData.toSkill());
		}
			
			return event;
		}
	
	
//	--------------------------------------SkillData----------------------------
	@Data
	@NoArgsConstructor
	public static class SkillData {
		private Long skillId;
		private String skillName;
		private String skillDescription;
		private int skillLevel;
		private Set<DrillData> drills = new HashSet<>();
		
		public SkillData(Skill skill) {
			this.skillId = skill.getSkillId();
			this.skillName = skill.getSkillName();
			this.skillDescription = skill.getSkillDescription();
			this.skillLevel = skill.getSkillLevel();
			
			for(Drill drill : skill.getDrills()) {
				this.drills.add(new DrillData(drill));
			}
		}
		public Skill toSkill() {
			Skill skill = new Skill();
			
			skill.setSkillId(skillId);
			skill.setSkillName(skillName);
			skill.setSkillDescription(skillDescription);
			skill.setSkillLevel(skillLevel);
			
			for(DrillData drillData : drills) {
				skill.getDrills().add(drillData.toDrill());
			}
			return skill;
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class DrillData {
		private Long drillId;
		private String drillName;
		private String drillDescription;
		private String drillEquipment;
		private int drillLevel;
		
		public DrillData(Drill drill){
			this.drillId = drill.getDrillId();
			this.drillName = drill.getDrillName();
			this.drillDescription = drill.getDrillDescription();
			this.drillEquipment = drill.getDrillEquipment();
		}
		public Drill toDrill() {
			Drill drill = new Drill();
			
			drill.setDrillId(drillId);
			drill.setDrillName(drillName);
			drill.setDrillDescription(drillDescription);
			drill.setDrillEquipment(drillEquipment);
		
			return drill;
		}
	}
	
}
