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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Drill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long drillId;
	
	private String drillName;
	private String drillDescription;
	private String drillEquipment;
	private String drillLevel;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "skill_drill",
		joinColumns = @JoinColumn(name = "skill_id"),
		inverseJoinColumns = @JoinColumn(name = "drill_id")
	)	
	private Set<Skill> skills = new HashSet<>();
}
