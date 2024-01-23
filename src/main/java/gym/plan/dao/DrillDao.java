package gym.plan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gym.plan.entity.Drill;

public interface DrillDao extends JpaRepository<Drill, Long> {

	Optional<Drill> findByDrillName(String drillName);

}
