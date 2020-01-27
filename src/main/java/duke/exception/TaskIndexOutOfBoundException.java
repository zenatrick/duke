package duke.exception;

/**
 * Signals that the task index specified is out of bound for that TaskList.
 */
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
