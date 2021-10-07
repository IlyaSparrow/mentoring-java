package com.epam.event.mapper;

import com.epam.event.model.contracts.Event;
import com.epam.event.model.dto.EventEntity;

public class EventToEntityMapper {

    public static EventEntity map(final Event eventEntity){
        return EventEntity.builder()
                .id(eventEntity.getId())
                .eventType(eventEntity.getEventType())
                .dateTime(eventEntity.getDateTime())
                .place(eventEntity.getPlace())
                .speaker(eventEntity.getSpeaker())
                .title(eventEntity.getTitle())
                .build();
    }
}
