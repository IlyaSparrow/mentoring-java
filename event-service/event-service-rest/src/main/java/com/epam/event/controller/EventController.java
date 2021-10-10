package com.epam.event.controller;

import com.epam.event.model.contracts.Event;
import com.epam.event.service.api.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.epam.event.util.LinkUtil.addLinks;

@Tag(name = "event")
@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get an event by its id.")
    @ApiResponse(responseCode = "200", description = "Event was successfully received.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    @ApiResponse(responseCode = "404", description = "Event entity does not exist.")
    public EntityModel<Event> getEventById(@PathVariable("id") Long id) {
        Event event = eventService.getEventByID(id);
        Link link = addLinks(EventController.class, event);
        return EntityModel.of(event, link);
    }

    @PatchMapping(value = "/{id}/update")
    @ApiResponse(responseCode = "200", description = "Event was successfully updated.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    @ApiResponse(responseCode = "404", description = "Event entity does not exist.")
    public EntityModel<Event> updateEvent(@PathVariable("id") Long id, Event eventEntity) {
        eventEntity.setId(id);

        Event event = eventService.updateEvent(eventEntity);

        Link link = addLinks(EventController.class, event);
        return EntityModel.of(event, link);
    }

    @DeleteMapping(value = "/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
