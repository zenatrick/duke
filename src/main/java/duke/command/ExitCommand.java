package duke.command;

import duke.common.Messages;
import duke.task.TaskList;

class ExitCommand implements Command {
    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        return new CommandResponse(Messages.GOODBYE_MSG);
    }
}
