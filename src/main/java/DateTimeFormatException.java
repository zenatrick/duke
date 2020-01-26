public class DateTimeFormatException extends DukeException {

    @Override
    public String[] getMessages() {
        return new String[]{
            "☹ OOPS!!! I don't understand the time entered.",
            "  Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }
}
