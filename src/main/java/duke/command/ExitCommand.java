package duke.command;

import duke.task.TaskList;

import static duke.common.Messages.GOODBYE_MSG;

public class ExitCommand implements Command {
    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        return new CommandResponse(GOODBYE_MSG);
    }
}
