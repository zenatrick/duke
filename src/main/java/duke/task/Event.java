package duke.task;

import java.time.LocalDateTime;

import duke.common.DateTimeFormat;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String serialize() {
        return String.format("E,%d,%s,%s", isDone ? 1 : 0, description, at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at.format(DateTimeFormat.OUTPUT_DATE_TIME_FORMAT));
    }
}