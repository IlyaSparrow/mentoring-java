package com.epam.event.service.api;

import com.epam.event.contracts.Event;

import java.util.List;

/**
 * EventEntity service interface.
 *
 * @author Ilya Vorobyeu
 * @version 1.0.0
 */
public interface EventService {

    /**
     * Create new eventEntity instance.
     *
     * @param event eventEntity to create
     * @return created eventEntity entity
     */
    Event createEvent(Event event);

    /**
     * Update existing eventEntity.
     *
     * @param event eventEntity to update
     * @return updated eventEntity entity
     */
    Event updateEvent(Event event);

    /**
     * Get event by id.
     *
     * @param id event id
     * @return event entity
     */
    Event getEventByID(Long id);

    /**
     * Delete event by id.
     *
     * @param id event id
     */
    void deleteEvent(Long id);

    /**
     * Get all events.
     *
     * @return list of all event entities
     */
    List<Event> getAllEvents();

    /**
     * Get all events by title.
     *
     * @param title event title
     * @return list of event entities
     */
    List<Event> getAllEventsByTitle(String title);
}
