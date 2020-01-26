public class UnexpectedTaskFormatException extends DukeException {
    @Override
    public String[] getMessages() {
        return new String[]{"Error encountered while parsing save file."};
    }
}
