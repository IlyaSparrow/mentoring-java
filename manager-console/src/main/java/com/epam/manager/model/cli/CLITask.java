package com.epam.manager.model.cli;

import com.epam.manager.model.TaskCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CLITask {

    private String id;
    private LocalDate dateOfCreation;
    private LocalDate deadline;
    private String name;
    private String description;
    private TaskCategory category;
    private List<CLISubtask> subtasks;
}
