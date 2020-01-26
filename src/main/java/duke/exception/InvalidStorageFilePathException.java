package duke.exception;

public class InvalidStorageFilePathException extends DukeException {
    /**
     * Constructs a new InvalidStorageFilePathException with the specified array of messages.
     *
     * @param messages The detail array of messages.
     */
    public InvalidStorageFilePathException(String... messages) {
        super(messages);
    }
}
