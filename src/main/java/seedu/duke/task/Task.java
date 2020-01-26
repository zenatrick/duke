package seedu.duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; // "return ✓ or ✘ symbols;
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
