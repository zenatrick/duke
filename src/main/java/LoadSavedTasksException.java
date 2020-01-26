public class LoadSavedTasksException extends DukeException {
    @Override
    public String[] getMessages() {
        return new String[]{"☹ OOPS!!! Error encountered while trying to load save tasks from save file."};
    }
}
