package duke.exception;

/**
 * Signals that the path of the storage file is invalid.
 */
public class InvalidStorageFilePathException extends DukeException {
    /**
     * Constructs a new InvalidStorageFilePathException with the specified error message.
     *
     * @param message The detail error message.
     */
    public InvalidStorageFilePathException(String message) {
        super(message);
    }
}
