package com.epam.manager.shell;

import com.epam.manager.model.cli.CLITask;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ShellHelper {

    @Value("${shell.out.info}")
    public String infoColor;

    @Value("${shell.out.success}")
    public String successColor;

    @Value("${shell.out.warning}")
    public String warningColor;

    @Value("${shell.out.error}")
    public String errorColor;

    @Lazy
    @Autowired
    private Terminal terminal;

    public void printTask(CLITask task) {
        printInfo("__________________________________________________");
        if (Objects.nonNull(task.getId()) && !task.getId().isEmpty()) {
            printSuccess("id: " + task.getId());
        }
        print("name: " + task.getName());
        print("description: " + task.getDescription());
        print("category: " + task.getCategory());
        print("deadline: " + task.getDeadline());
        print("created: " + task.getDateOfCreation());
        if (Objects.nonNull(task.getSubtasks()) && !task.getSubtasks().isEmpty()) {
            printInfo("subtasks: {");
            task.getSubtasks().forEach(subtask -> {
                printInfo("\t[");
                print("\t\tname: " + subtask.getName());
                print("\t\tdescription: " + subtask.getDescription());
                printInfo("\t]");
            });
            printInfo("}");
        }
    }

    public void printSuccess(String message) {
        print(message, PromptColor.valueOf(successColor));
    }

    public void printInfo(String message) {
        print(message, PromptColor.valueOf(infoColor));
    }

    public void printWarning(String message) {
        print(message, PromptColor.valueOf(warningColor));
    }

    public void printError(String message) {
        print(message, PromptColor.valueOf(errorColor));
    }

    public void print(String message) {
        print(message, null);
    }

    public void print(String message, PromptColor color) {
        String toPrint = message;
        if (color != null) {
            toPrint = getColored(message, color);
        }
        terminal.writer().println(toPrint);
        terminal.flush();
    }

    public String getColored(String message, PromptColor color) {
        return (new AttributedStringBuilder()).append(message, AttributedStyle.DEFAULT.foreground(color.toJlineAttributedStyle())).toAnsi();
    }
}
