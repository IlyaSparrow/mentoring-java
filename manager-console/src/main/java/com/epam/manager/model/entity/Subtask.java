package com.epam.manager.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Subtask {

    private String name;
    private String description;
}
