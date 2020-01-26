package seedu.duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.duke.common.Messages;
import seedu.duke.exception.TaskIndexOutOfBoundException;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int taskIndex) throws TaskIndexOutOfBoundException {
        try {
            return taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundException(Messages.generateTaskIndexNotFoundMessage(taskIndex + 1));
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int taskIndex) throws TaskIndexOutOfBoundException {
        try {
            return taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundException(Messages.generateTaskIndexNotFoundMessage(taskIndex + 1));
        }
    }

    public Stream<Task> stream() {
        return taskList.stream();
    }
}
