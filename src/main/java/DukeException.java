public abstract class DukeException extends Exception {
    public DukeException() {
        super("An error occurred in the program.");
    }

    public abstract String[] getMessages();
}
