package seedu.duke.storage;

import java.util.stream.Collectors;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Encodes the TaskList object into a string of data to be saved in the storage file.
 */
class TaskEncoder {
    /**
     * Encodes the specified TaskList into a string of data that is readable and can be decoded.
     *
     * @param taskList The TaskList to be encoded.
     * @return The string of data that represents the encoded TaskList object.
     */
    static String encodeTasksList(TaskList taskList) {
        return taskList.stream()
                .map(Task::serialize)
                .collect(Collectors.joining("\n"));
    }
}
