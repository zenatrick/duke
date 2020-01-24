public class InvalidArgumentException extends DukeException {
    private String[] messages;

    /**
     * Constructs a new InvalidArgumentException of a specified command with the specified message.
     *
     * @param command The command which has received invalid arguments.
     * @param message The detail message.
     */
    public InvalidArgumentException(String command, String message) {
        switch (command) {
        case "done":
            messages = new String[]{
                message,
                "To mark a task as done, tell me in this format: ",
                "  done [task_index]"
            };
            break;
        case "delete":
            messages = new String[]{
                message,
                "To delete a task, tell me in this format: ",
                "  delete [task_index]"
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
