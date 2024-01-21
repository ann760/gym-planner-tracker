package gym.plan.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillId;
	
	@EqualsAndHashCode.Exclude
	private String skillName;
	
	@EqualsAndHashCode.Exclude
	private String skillDescription;
	
	@EqualsAndHashCode.Exclude
	private int skillLevel;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "skill_drill",
		joinColumns = @JoinColumn(name = "skill_id"),
		inverseJoinColumns = @JoinColumn(name = "drill_id")
		)
	private Set<Drill> drills = new HashSet<>();
	
}
