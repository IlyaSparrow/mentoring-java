package com.epam.event;

import com.epam.event.service.api.EventService;
import com.epam.event.service.impl.EventServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfiguration {

    @Bean
    public EventService eventService() {
        return new EventServiceImpl();
    }
}
