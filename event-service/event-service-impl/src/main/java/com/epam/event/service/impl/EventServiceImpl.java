package com.epam.event.service.impl;

import com.epam.event.exception.EventNotFoundException;
import com.epam.event.mapper.EventToContractMapper;
import com.epam.event.mapper.EventToEntityMapper;
import com.epam.event.model.contracts.Event;
import com.epam.event.model.dto.EventEntity;
import com.epam.event.repository.EventRepository;
import com.epam.event.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    @Transactional
    public Event createEvent(final Event event) {
        final EventEntity entity = EventToEntityMapper.map(event);
        return EventToContractMapper.map(repository.save(entity));
    }

    @Override
    @Transactional
    public Event updateEvent(final Event event) {
        final EventEntity entity = repository.findById(event.getId())
                .orElseThrow(() -> new EventNotFoundException("event does not exist"));
        event.setId(entity.getId());
        event.setEventType(entity.getEventType());
        event.setDateTime(entity.getDateTime());
        event.setPlace(entity.getPlace());
        event.setTitle(entity.getTitle());
        return EventToContractMapper.map(repository.save(entity));
    }

    @Override
    public Event getEventByID(Long id) {
        return EventToContractMapper.map(repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("event does not exist")));
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        final EventEntity entity = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("event does not exist"));
        repository.delete(entity);
    }

    @Override
    @Transactional
    public List<Event> getAllEvents() {
        return repository.findAll().stream()
                .filter(Objects::nonNull)
                .map(EventToContractMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Event> getAllEventsByTitle(String title) {
        return repository.findAllByTitle(title).stream()
                .filter(Objects::nonNull)
                .map(EventToContractMapper::map)
                .collect(Collectors.toList());
    }
}
