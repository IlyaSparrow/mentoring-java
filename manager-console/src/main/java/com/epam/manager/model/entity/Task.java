package com.epam.manager.model.entity;

import com.epam.manager.model.TaskCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "task")
@Builder
@Getter
@Setter
public class Task {

    @Id
    private String id;

    @Field(value = "created_at")
    private LocalDate dateOfCreation;

    @Field(value = "deadline")
    private LocalDate deadline;

    @Field(value = "name")
    private String name;

    @Field(value = "description")
    private String description;

    @Field(value = "category")
    private TaskCategory category;

    @Field(value = "subtasks")
    private List<Subtask> subtasks;
}
