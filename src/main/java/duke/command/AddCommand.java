package duke.command;

import java.time.LocalDateTime;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import static duke.common.Messages.generateAddSuccessMessage;


class AddCommand implements Command {
    static final int TYPE_TODO = 1;
    static final int TYPE_DEADLINE = 2;
    static final int TYPE_EVENT = 3;

    private final Task taskToAdd;

    AddCommand(int type, String description, LocalDateTime time) {
        switch (type) {
        case TYPE_TODO:
            taskToAdd = new Todo(description);
            break;
        case TYPE_DEADLINE:
            taskToAdd = new Deadline(description, time);
            break;
        case TYPE_EVENT:
            taskToAdd = new Event(description, time);
            break;
        default:
            throw new AssertionError("CommandParser will ensure the correct type is passed into the constructor.");
        }
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        taskList.add(taskToAdd);
        return new CommandResponse(generateAddSuccessMessage(taskToAdd.toString(), taskList.size()));
    }
}
