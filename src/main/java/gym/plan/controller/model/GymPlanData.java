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
		eventId = event.getEventId();
		eventName = event.getEventName();
		eventDescription = event.getEventDescription();
		
		for(Skill skill : event.getSkills()) {
			skills.add(new SkillData(skill));
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class SkillData {
		private Long skillId;
		private String skillName;
		private String skillDescription;
		private int skillLevel;
		private Set<DrillData> drills = new HashSet<>();
		
		public SkillData(Skill skill) {
			skillId = skill.getSkillId();
			skillName = skill.getSkillName();
			skillDescription = skill.getSkillDescription();
			skillLevel = skill.getSkillLevel();
			
			for(Drill drill : skill.getDrills()) {
				drills.add(new DrillData(drill));
			}
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
			drillId = drill.getDrillId();
			drillName = drill.getDrillName();
			drillDescription = drill.getDrillDescription();
			drillEquipment = drill.getDrillEquipment();
		}
	}
	
}
