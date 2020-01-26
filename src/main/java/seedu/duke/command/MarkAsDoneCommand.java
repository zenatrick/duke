package seedu.duke.command;

import seedu.duke.common.Messages;
import seedu.duke.exception.IncorrectCommandException;
import seedu.duke.exception.TaskIndexOutOfBoundException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class MarkAsDoneCommand implements Command {

    private final int taskIndex;

    MarkAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) throws IncorrectCommandException {
        try {
            Task task = taskList.get(taskIndex);
            task.markAsDone();
            return new CommandResponse(Messages.generateDoneSuccessMessage(task.toString()));
        } catch (TaskIndexOutOfBoundException e) {
            throw new IncorrectCommandException(e.getMessages());
        }
    }
}