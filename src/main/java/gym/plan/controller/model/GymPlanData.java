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
	private Set<SkillResponse> skills = new HashSet<>();
	
	public GymPlanData(Event event) {
		eventId = event.getEventId();
		eventName = event.getEventName();
		eventDescription = event.getEventDescription();
		
		for(Skill skill : event.getSkills()) {
			skills.add(new SkillResponse(skill));
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class SkillResponse {
		private Long skillId;
		private String skillName;
		private String skillDescription;
		private int skillLevel;
		private Set<String> drills = new HashSet<>();
		
		SkillResponse(Skill skill) {
			skillId = skill.getSkillId();
			skillName = skill.getSkillName();
			skillDescription = skill.getSkillDescription();
			skillLevel = skill.getSkillLevel();
			
			for(Drill drill : skill.getDrills()) {
				drills.add(drill.getDrillName());
			}
		}
	}
}
