package seedu.duke.command;

import seedu.duke.exception.IncorrectCommandException;
import seedu.duke.task.TaskList;

/**
 * An interface for the commands of this application.
 */
public interface Command {
    /**
     * Returns an anonymous dummy Command that is not an ExitCommand.
     * Calling isExitCommand() will return false and calling execute() will return null.
     *
     * @return An anonymous dummy Command that is not an ExitCommand.
     */
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

    /**
     * Returns true of this command is an ExitCommand.
     * Otherwise, returns false.
     *
     * @return True only if this command is an ExitCommand.
     */
    boolean isExitCommand();

    /**
     * Executes this command and returns the CommandResponse.
     *
     * @param taskList The TaskList that this command should operate on.
     * @return The CommandResponse after executing this command.
     * @throws IncorrectCommandException If an error occurs when executing the command.
     */
    CommandResponse execute(TaskList taskList) throws IncorrectCommandException;
}
