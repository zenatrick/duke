public class InvalidCommandException extends DukeException {
    private String[] messages;

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
