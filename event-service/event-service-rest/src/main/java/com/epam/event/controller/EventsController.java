package com.epam.event.controller;

import com.epam.event.model.contracts.Event;
import com.epam.event.service.api.EventService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import static com.epam.event.util.LinkUtil.addLinks;

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
    public EntityModel<Event> createEvent(@RequestBody Event eventEntity) {
        Event event = eventService.createEvent(eventEntity);
        Link link = addLinks(EventsController.class, event);

        return EntityModel.of(event, link);
    }

    @GetMapping(value = "/all")
    @ApiResponse(responseCode = "200", description = "Events were successfully received.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    public CollectionModel<Event> getAllEvents() {
        final List<Event> events = eventService.getAllEvents();
        Link link = addLinks(EventsController.class, events);
        return CollectionModel.of(events, link);
    }

    @GetMapping(params = {"title"})
    @ApiResponse(responseCode = "200", description = "Events were successfully received.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    public CollectionModel<Event> getAllEventsByTitle(@RequestParam("title") String title) {
        final List<Event> events = eventService.getAllEventsByTitle(title);
        Link link = addLinks(EventsController.class, events);
        return CollectionModel.of(events, link);
    }
}
