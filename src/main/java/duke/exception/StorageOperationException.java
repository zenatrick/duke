package duke.exception;

/**
 * Signals that an error has occurred while performing a storage operation.
 */
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
