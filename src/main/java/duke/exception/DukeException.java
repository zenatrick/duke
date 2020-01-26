package duke.exception;

public abstract class DukeException extends Exception {
    protected final String[] messages;

    public DukeException(String... messages) {
        super("An error occurred in the program.");
        this.messages = messages;
    }

    public String[] getMessages() {
        return messages;
    }
}
