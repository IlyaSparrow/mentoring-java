package com.epam.event.util;

import com.epam.event.contracts.Event;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class LinkUtil {

    public static Link addLinks(Class<?> type, final List<Event> events) {
        for (final Event event : events) {
            String eventId = event.getId().toString();
            Link selfLink = linkTo(type).slash(eventId).withSelfRel();
            event.add(selfLink);
        }
        return linkTo(type).withSelfRel();
    }

    public static Link addLinks(Class<?> type, final Event event) {
        String eventId = event.getId().toString();
        Link selfLink = linkTo(type).slash(eventId).withSelfRel();
        event.add(selfLink);
        return linkTo(type).withSelfRel();
    }
}
