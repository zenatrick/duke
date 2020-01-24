public class IncompleteCommandException extends DukeException {
    private String[] messages;

    /**
     * Constructs a new IncompleteCommandException of a specified command with the specified message.
     *
     * @param command The command which is incomplete.
     * @param message The detail message.
     */
    public IncompleteCommandException(String command, String message) {
        switch (command) {
        case "todo":
            messages = new String[]{
                message,
                "To add a todo, tell me in this format: ",
                "  todo [description]"
            };
            break;
        case "deadline":
            messages = new String[]{
                message,
                "To add a deadline, tell me in this format: ",
                "  todo [description] /by [date/time]"
            };
            break;
        case "event":
            messages = new String[]{
                message,
                "To add an event, tell me in this format: ",
                "  todo [description] /at [date/time]"
            };
            break;
        default: // Never reached
            messages = new String[]{
                "An unexpected error occurred."
            };
        }
    }

    @Override
    public String[] getMessages() {
        return messages;
    }
}
