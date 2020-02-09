package duke.command;

import duke.exception.IncorrectCommandException;
import duke.exception.TaskIndexOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

import static duke.common.Messages.generateDeleteSuccessMessage;

class DeleteCommand implements Command {

    private final int taskIndex;
    private boolean isExecuted;
    private Task deletedTask;

    DeleteCommand(int taskIndex) {
        isExecuted = false;
        deletedTask = null;
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public boolean isUndoCommand() {
        return false;
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public CommandResponse execute(TaskList taskList) throws IncorrectCommandException {
        assert !isExecuted : "Executing already executed command.";
        try {
            deletedTask = taskList.remove(taskIndex);
        } catch (TaskIndexOutOfBoundException e) {
            throw new IncorrectCommandException(e.getMessages());
        }
        isExecuted = true;
        return new CommandResponse(generateDeleteSuccessMessage(deletedTask.toString(), taskList.size()));
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        assert isExecuted : "Undoing not executed command.";
        taskList.add(taskIndex, deletedTask);
        deletedTask = null;
        isExecuted = false;
        return new CommandResponse("undo delete command");
    }
}
