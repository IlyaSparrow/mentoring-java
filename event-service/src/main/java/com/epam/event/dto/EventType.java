package com.epam.event.dto;

public enum EventType {

    SINGLE("single"),
    REGULAR("regular");

    private final String name;

    EventType(final String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }
}
