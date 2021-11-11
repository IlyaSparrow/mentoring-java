# Task manager console

Task manager console used Spring Shell framework. Manager console connected to Database via Spring ORM for MongoDB(
spring-boot-starter-mongo).

# Shell commands

#### Built-In Commands

* clear: Clear the shell screen.
* exit, quit: Exit the shell.
* help: Display help about available commands.
* history: Display or save the history of previously run commands
* script: Read and execute commands from a file.
* stacktrace: Display the full stacktrace of the last error.

#### Subtask Commands

* create-subtask `taskId`: Create new subtask.
* delete-subtask `taskId` `subtaskName`: Delete subtask.

#### Task Commands

* create-task: Create new task.
* delete `taskId`: Delete task by id.
* get-all: Get all task list.
* get-by-id `taskId`: Get task by id.
* get-overdue: Get overdue task.