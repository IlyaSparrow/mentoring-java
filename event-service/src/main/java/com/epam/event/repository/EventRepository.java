package com.epam.event.repository;

import com.epam.event.dto.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    /**
     * Get list of events by its title.
     *
     * @param title of the event
     * @return List of event entities
     */
    List<EventEntity> findAllByTitle(String title);

    /**
     * Get event by id.
     *
     * @param id event id
     * @return event entity
     */
    Optional<EventEntity> findById(Long id);
}
