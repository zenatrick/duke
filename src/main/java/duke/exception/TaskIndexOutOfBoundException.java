package duke.exception;

public class TaskIndexOutOfBoundException extends DukeException {
    /**
     * Constructs a new TaskIndexOutOfBoundException with the specified array of messages.
     *
     * @param messages The detail array of messages.
     */
    public TaskIndexOutOfBoundException(String... messages) {
        super(messages);
    }
}
