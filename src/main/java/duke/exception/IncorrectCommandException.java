package duke.exception;

/**
 * Signals that there is an error in the user's input command.
 */
public class IncorrectCommandException extends DukeException {
    /**
     * Constructs a new IncorrectCommandException with the specified array of messages.
     *
     * @param messages The detail array of messages.
     */
    public IncorrectCommandException(String... messages) {
        super(messages);
    }
}
