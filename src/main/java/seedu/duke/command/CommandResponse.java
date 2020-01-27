package seedu.duke.command;

/**
 * Represents the response given to the user after executing a command.
 */
public class CommandResponse {
    private String[] response;

    CommandResponse(String... response) {
        this.response = response;
    }

    /**
     * Returns the array of response messages.
     *
     * @return The array of response messages.
     */
    public String[] get() {
        return response;
    }
}
