package com.epam.manager.shell;

import com.epam.manager.model.cli.CLISubtask;
import com.epam.manager.model.cli.CLITask;
import com.epam.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@ShellComponent
public class SubtaskCommands {

    @Autowired
    private InputReader inputReader;

    @Autowired
    private ShellHelper shellHelper;

    @Autowired
    private TaskService service;

    @ShellMethod("Create new subtask.")
    public void createSubtask(@ShellOption({"-ID", "--id"}) String taskId) {

        CLISubtask subtask = new CLISubtask();

        if (!service.existsById(taskId)) {
            shellHelper.printError(String.format("Task with id='%s' does not exists --> ABORTING", taskId));
            return;
        }

        CLITask cliTask = service.findById(taskId);

        while (isNull(subtask.getName()) || isBlank(subtask.getName())) {
            String name = inputReader.prompt("Name", "", false);
            if (StringUtils.hasText(name)) {
                subtask.setName(name);
            } else {
                shellHelper.printWarning("Description can not be empty");
            }
        }

        while (isNull(subtask.getDescription()) || isBlank(subtask.getDescription())) {
            String description = inputReader.prompt("Description", "", false);
            if (StringUtils.hasText(description)) {
                subtask.setDescription(description);
            } else {
                shellHelper.printWarning("Description can not be empty");
            }
        }

        shellHelper.printInfo("\nUpdating your task:");

        if (Objects.isNull(cliTask.getSubtasks())) {
            cliTask.setSubtasks(new ArrayList<>());
        }

        cliTask.getSubtasks().add(subtask);

        CLITask update = service.update(cliTask);

        shellHelper.printSuccess("Task updated, task sid = " + update.getId());
        shellHelper.printTask(cliTask);
    }

    @ShellMethod("Delete subtask.")
    public void deleteSubtask(@ShellOption({"-ID", "--id"}) String taskId, @ShellOption({"-N", "--name"}) String name) {

        CLISubtask subtask = new CLISubtask();

        if (!service.existsById(taskId)) {
            shellHelper.printError(String.format("Task with id='%s' does not exists --> ABORTING", taskId));
            return;
        }

        CLITask cliTask = service.findById(taskId);

        if (containsSubtask(cliTask, name)) {
            cliTask.getSubtasks().remove(getSubtask(cliTask, name));
        }

        shellHelper.printInfo("\nUpdating your task:");

        CLITask update = service.update(cliTask);

        shellHelper.printSuccess(String.format("Subtask with name %s was deleted from task with id=%s", name, taskId));
        shellHelper.printTask(cliTask);
    }

    private boolean containsSubtask(CLITask cliTask, String name) {
        if (Objects.isNull(cliTask.getSubtasks())) {
            shellHelper.printError(String.format("Task with id='%s' does not contains subtasks --> ABORTING",
                    cliTask.getId()));
            return false;
        }

        return cliTask.getSubtasks().stream()
                .anyMatch(task -> name.equals(task.getName()));
    }

    private CLISubtask getSubtask(CLITask cliTask, String name) {
        return cliTask.getSubtasks().stream()
                .filter(task -> name.equals(task.getName()))
                .findFirst()
                .get();
    }
}
