package duke.task;

import java.time.LocalDateTime;

import duke.common.DateTimeFormat;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String serialize() {
        return String.format("D,%d,%s,%s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeFormat.OUTPUT_DATE_TIME_FORMAT));
    }
}
