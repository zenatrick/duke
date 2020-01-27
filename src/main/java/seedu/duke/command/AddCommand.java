package seedu.duke.command;

import java.time.LocalDateTime;

import seedu.duke.common.Messages;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;


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
        return new CommandResponse(Messages.generateAddSuccessMessage(taskToAdd.toString(), taskList.size()));
    }
}
