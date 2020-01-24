public class InvalidCommandException extends DukeException {
    private String[] messages;

    /**
     * Constructs a new InvalidCommandException with the specified message.
     *
     * @param message The detail message.
     */
    public InvalidCommandException(String message) {
        messages = new String[]{
            message
        };
    }

    @Override
    public String[] getMessages() {
        return messages;
    }
}
