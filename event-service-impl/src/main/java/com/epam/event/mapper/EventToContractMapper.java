package com.epam.event.mapper;

import com.epam.event.model.contracts.Event;
import com.epam.event.model.dto.EventEntity;

public class EventToContractMapper {

    public static Event map(final EventEntity eventEntity){
        return Event.builder()
                .id(eventEntity.getId())
                .eventType(eventEntity.getEventType())
                .dateTime(eventEntity.getDateTime())
                .place(eventEntity.getPlace())
                .speaker(eventEntity.getSpeaker())
                .title(eventEntity.getTitle())
                .build();
    }
}
