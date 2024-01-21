package gym.plan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gym.plan.entity.Event;

public interface EventDao extends JpaRepository<Event, Long> {

	Optional<Event> findByEventName(String eventName);

}
