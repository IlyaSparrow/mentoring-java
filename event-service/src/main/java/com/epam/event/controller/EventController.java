package com.epam.event.controller;

import com.epam.event.contracts.Event;
import com.epam.event.service.api.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "event")
@RestController
@RequestMapping(value = "/event/{id}")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    @Operation(summary = "Get an event by its id.")
    @ApiResponse(responseCode = "200", description = "Event was successfully received.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    @ApiResponse(responseCode = "404", description = "Event entity does not exist.")
    public Event getEventById(@PathVariable("id") Long id) {
        return eventService.getEventByID(id);
    }

    @PutMapping
    @ApiResponse(responseCode = "200", description = "Event was successfully updated.")
    @ApiResponse(responseCode = "400", description = "The request was invalid.")
    @ApiResponse(responseCode = "404", description = "Event entity does not exist.")
    public Event updateEvent(@PathVariable("id") Long id, Event eventEntity) {
        eventEntity.setId(id);
        return eventService.updateEvent(eventEntity);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
    }
}
