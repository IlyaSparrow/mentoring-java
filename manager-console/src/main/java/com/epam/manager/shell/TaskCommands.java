package com.epam.manager.shell;

import com.epam.manager.model.TaskCategory;
import com.epam.manager.model.cli.CLITask;
import com.epam.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@ShellComponent
public class TaskCommands {

    @Autowired
    private InputReader inputReader;

    @Autowired
    private ShellHelper shellHelper;

    @Autowired
    private TaskService service;

    @ShellMethod("Create new task.")
    public void createTask() {

        CLITask task = new CLITask();

        while (isNull(task.getName()) || isBlank(task.getName())) {
            String name = inputReader.prompt("Name", "", false);
            if (StringUtils.hasText(name)) {
                if (service.existsByName(name)) {
                    shellHelper.printError(String.format("Task with name='%s' already exists --> ABORTING", name));
                } else {
                    task.setName(name);
                }
            } else {
                shellHelper.printWarning("Description can not be empty");
            }
        }

        while (isNull(task.getDescription()) || isBlank(task.getDescription())) {
            String description = inputReader.prompt("Description", "", false);
            if (StringUtils.hasText(description)) {
                task.setDescription(description);
            } else {
                shellHelper.printWarning("Description can not be empty");
            }
        }

        while (isNull(task.getCategory())) {
            String value = inputReader.prompt("Category(CRITICAL, MINOR)", "MINOR", false);
            if (StringUtils.hasText(value)) {
                TaskCategory category = TaskCategory.valueOf(value.toUpperCase());
                task.setCategory(category);
            } else {
                shellHelper.printWarning("Category can not be empty");
            }
        }

        while (isNull(task.getDeadline())) {
            String value = inputReader.prompt("DeadLine", LocalDate.now().toString(), false);
            if (StringUtils.hasText(value)) {
                LocalDate deadline = LocalDate.parse(value);
                task.setDeadline(deadline);
            } else {
                shellHelper.printWarning("Category can not be empty");
            }
        }

        shellHelper.printInfo("\nCreating new Task:");
        shellHelper.printTask(task);

        CLITask created = service.create(task);
        shellHelper.printSuccess("Task created with id = " + created.getId());
    }

    @ShellMethod("Get all task list.")
    public void getAll() {
        shellHelper.printInfo("\nSearching for task list.");
        List<CLITask> taskList = service.getAll();
        taskList.forEach(cliTask -> shellHelper.printTask(cliTask));
    }

    @ShellMethod("Get task by id.")
    public void getById(@ShellOption({"-ID", "--id"}) String id) {
        CLITask task = service.findById(id);
        if (Objects.isNull(task)) {
            shellHelper.printError(String.format("Task with id='%s' not exists --> ABORTING", id));
            return;
        }

        shellHelper.printTask(task);
    }

    @ShellMethod("Delete task by id.")
    public void delete(@ShellOption({"-ID", "--id"}) String id) {
        if (!service.existsById(id)) {
            shellHelper.printError(String.format("Task with id='%s' not exists --> ABORTING", id));
            return;
        }

        service.delete(id);

        shellHelper.printSuccess(String.format("Task with id=%s deleted.", id));
    }

    @ShellMethod("Delete task by id.")
    public void getOverdue() {
        final List<CLITask> cliTasks = service.getOverdueTasks();
        cliTasks.forEach(task -> shellHelper.printTask(task));
    }
}
