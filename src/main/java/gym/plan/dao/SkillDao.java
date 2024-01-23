package gym.plan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gym.plan.entity.Skill;

public interface SkillDao extends JpaRepository<Skill, Long> {

	Optional<Skill> findBySkillName(String skillName);
}
