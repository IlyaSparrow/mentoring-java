package com.epam.event.controller;

import com.epam.event.contracts.Event;
import com.epam.event.service.api.EventService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Tag(name = "events")
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Event was successfully created.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    public Event createEvent(@RequestBody Event eventEntity) {
        return eventService.createEvent(eventEntity);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Events were successfully received.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    public CollectionModel<Event> getAllEvents() {
        final List<Event> events = eventService.getAllEvents();

        for (final Event event : events) {
            String customerId = event.getId().toString();
            Link selfLink = linkTo(EventsController.class).slash(customerId)
                    .withSelfRel();
            event.add(selfLink);
        }

        Link link = linkTo(EventsController.class).withSelfRel();
        CollectionModel<Event> result = CollectionModel.of(events, link);
        return result;
    }

    @GetMapping(params = {"title"})
    @ApiResponse(responseCode = "200", description = "Events were successfully received.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    public List<Event> getAllEventsByTitle(@RequestParam("title") String title) {
        return eventService.getAllEventsByTitle(title);
    }
}
