package seedu.duke.exception;

public class StorageOperationException extends DukeException {
    /**
     * Constructs a new StorageOperationException with the specified array of messages.
     *
     * @param messages The detail array of messages.
     */
    public StorageOperationException(String... messages) {
        super(messages);
    }
}
