package com.epam.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(final String message) {
        super(message);
    }
}
