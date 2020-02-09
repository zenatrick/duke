package duke.command;

import duke.exception.IncorrectCommandException;
import duke.exception.TaskIndexOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

import static duke.common.Messages.generateDoneSuccessMessage;

class MarkAsDoneCommand implements Command {

    private final int taskIndex;
    private boolean isExecuted;

    MarkAsDoneCommand(int taskIndex) {
        isExecuted = false;
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
            Task task = taskList.get(taskIndex);
            task.markAsDone();
            isExecuted = true;
            return new CommandResponse(generateDoneSuccessMessage(task.toString()));
        } catch (TaskIndexOutOfBoundException e) {
            throw new IncorrectCommandException(e.getMessages());
        }
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        assert isExecuted : "Undoing not executed command.";
        try {
            Task task = taskList.get(taskIndex);
            task.markAsNotDone();
        } catch (TaskIndexOutOfBoundException e) {
            assert false : e; // will not reach here
        }
        isExecuted = false;
        return new CommandResponse("undo mark as done command");
    }
}