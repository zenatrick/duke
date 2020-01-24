public class InvalidArgumentException extends DukeException {
    private String[] messages;

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
