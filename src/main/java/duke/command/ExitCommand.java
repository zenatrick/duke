package duke.command;

import duke.task.TaskList;

import static duke.common.Messages.GOODBYE_MSG;

class ExitCommand implements Command {
    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public boolean isUndoCommand() {
        return false;
    }

    @Override
    public boolean canBeUndone() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        return new CommandResponse(GOODBYE_MSG);
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        throw new AssertionError("Undo for exit command will not be called.");
    }
}
