package duke.command;

import duke.exception.IncorrectCommandException;
import duke.exception.TaskIndexOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

import static duke.common.Messages.generateDoneSuccessMessage;

class MarkAsDoneCommand implements Command {

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
            return new CommandResponse(generateDoneSuccessMessage(task.toString()));
        } catch (TaskIndexOutOfBoundException e) {
            throw new IncorrectCommandException(e.getMessages());
        }
    }
}