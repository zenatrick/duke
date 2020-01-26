package seedu.duke.command;

import seedu.duke.exception.IncorrectCommandException;
import seedu.duke.task.TaskList;

public interface Command {
    static Command generateNonExitCommand() {
        return new Command() {
            @Override
            public boolean isExitCommand() {
                return false;
            }

            @Override
            public CommandResponse execute(TaskList taskList) {
                return null;
            }
        };
    }

    boolean isExitCommand();

    CommandResponse execute(TaskList taskList) throws IncorrectCommandException;
}
