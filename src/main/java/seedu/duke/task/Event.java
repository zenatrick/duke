package seedu.duke.task;

import java.time.LocalDateTime;

import seedu.duke.common.DateTimeFormat;

/**
 * Represents an Event in the application.
 * An Event is a type of Task which consist of a description and a time at which the Event is taking place.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructs a new Event task with the specified description and time.
     *
     * @param description The description of the Event.
     * @param at          The time at which the Event is taking place.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the serialized form of this Event in the form of a string.
     *
     * @return A string that is the serialized form of this Event.
     */
    @Override
    public String serialize() {
        return String.format("E,%d,%s,%s", isDone ? 1 : 0, description, at);
    }

    /**
     * Returns the serialized form of this Event in the form of a string.
     *
     * @return A string that is the serialized form of this Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at.format(DateTimeFormat.OUTPUT_DATE_TIME_FORMAT));
    }
}