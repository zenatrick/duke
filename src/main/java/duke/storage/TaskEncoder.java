package duke.storage;

import java.util.stream.Collectors;

import duke.task.Task;
import duke.task.TaskList;

public class TaskEncoder {
    static String encodeTasksList(TaskList taskList) {
        return taskList.stream()
                .map(Task::serialize)
                .collect(Collectors.joining("\n"));
    }
}
