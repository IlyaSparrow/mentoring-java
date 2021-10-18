package com.epam.manager.mapper;

import com.epam.manager.model.cli.CLISubtask;
import com.epam.manager.model.cli.CLITask;
import com.epam.manager.model.entity.Subtask;
import com.epam.manager.model.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public final class TaskMapper {

    public Task toEntity(final CLITask CLITask) {
        Task task = Task.builder()
                .category(CLITask.getCategory())
                .deadline(CLITask.getDeadline())
                .dateOfCreation(CLITask.getDateOfCreation())
                .description(CLITask.getDescription())
                .name(CLITask.getName())
                .id(CLITask.getId())
                .build();

        if (Objects.nonNull(CLITask.getSubtasks())) {
            task.setSubtasks(toSubtaskList(CLITask.getSubtasks()));
        }

        return task;
    }

    public CLITask toCLI(final Task task) {
        CLITask cliTask = CLITask.builder()
                .category(task.getCategory())
                .deadline(task.getDeadline())
                .dateOfCreation(task.getDateOfCreation())
                .description(task.getDescription())
                .name(task.getName())
                .id(task.getId())
                .dateOfCreation(task.getDateOfCreation())
                .build();

        if (Objects.nonNull(task.getSubtasks())) {
            cliTask.setSubtasks(toCLISubtaskList(task.getSubtasks()));
        }

        return cliTask;
    }

    public List<CLISubtask> toCLISubtaskList(final List<Subtask> subtasks) {
        return subtasks.stream()
                .map(this::toCLISubtask)
                .collect(Collectors.toList());
    }

    public CLISubtask toCLISubtask(final Subtask subtask) {
        return CLISubtask.builder()
                .name(subtask.getName())
                .description(subtask.getDescription())
                .build();
    }

    public List<Subtask> toSubtaskList(final List<CLISubtask> cliSubtasks) {
        return cliSubtasks.stream()
                .map(this::toSubtask)
                .collect(Collectors.toList());
    }

    public Subtask toSubtask(final CLISubtask cliSubtask) {
        return Subtask.builder()
                .name(cliSubtask.getName())
                .description(cliSubtask.getDescription())
                .build();
    }
}
