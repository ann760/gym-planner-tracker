package gym.plan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gym.plan.entity.Event;

public interface EventDao extends JpaRepository<Event, Long> {

}
