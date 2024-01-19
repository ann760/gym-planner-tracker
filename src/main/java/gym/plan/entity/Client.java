package gym.plan.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;
	
	private String clientFirstname;
	private String clientLastname;
	private int clientLevel;
	private String clientEmail;
	private String clientAddress;
	private String clientPhone;
	private String clientCity;
	private String clientState;
	private String clientZip;
	private String createdDate;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Achievement> achievements = new HashSet<>();
}
