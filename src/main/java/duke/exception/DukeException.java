package duke.exception;

/**
 * Signals that an error has occurred in the application.
 */
public abstract class DukeException extends Exception {
    protected final String[] messages;

    public DukeException(String... messages) {
        super("An error occurred in the application.");
        this.messages = messages;
    }

    public String[] getMessages() {
        return messages;
    }
}
