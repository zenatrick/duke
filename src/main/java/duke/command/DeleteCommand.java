package duke.command;

import duke.common.Messages;
import duke.exception.IncorrectCommandException;
import duke.exception.TaskIndexOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

class DeleteCommand implements Command {

    private final int taskIndex;

    DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) throws IncorrectCommandException {
        try {
            Task taskRemoved = taskList.remove(taskIndex);
            return new CommandResponse(Messages.generateDeleteSuccessMessage(taskRemoved.toString(), taskList.size()));
        } catch (TaskIndexOutOfBoundException e) {
            throw new IncorrectCommandException(e.getMessages());
        }
    }
}
