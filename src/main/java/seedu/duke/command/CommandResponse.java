package seedu.duke.command;

public class CommandResponse {
    private String[] response;

    CommandResponse(String... response) {
        this.response = response;
    }

    public String[] get() {
        return response;
    }
}
