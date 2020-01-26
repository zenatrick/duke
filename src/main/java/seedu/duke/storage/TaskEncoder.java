package seedu.duke.storage;

import java.util.stream.Collectors;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class TaskEncoder {
    static String encodeTasksList(TaskList taskList) {
        return taskList.stream()
                .map(Task::serialize)
                .collect(Collectors.joining("\n"));
    }
}
