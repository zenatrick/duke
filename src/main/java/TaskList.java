import java.util.ArrayList;
import java.util.List;

/**
 * A list to keep track of all the the tasks in the program Duke.
 */
public class TaskList {
    /**
     * List of tasks.
     */
    private List<Task> tasks;

    /**
     * Constructs a list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add the specified task to this list.
     *
     * @param task The task to be added to this list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index of this list.
     *
     * @param taskIdx The index of the task to be returned.
     * @return The task at the specified index of this list.
     * @throws TaskListIndexOutOfBoundsException If the taskIdx argument is out of range (taskIdx < 0 ||
     *                                           taskIdx >= size()).
     */
    public Task getTask(int taskIdx) throws TaskListIndexOutOfBoundsException {
        try {
            return tasks.get(taskIdx);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException(
                    String.format("☹ OOPS!!! The task of index %d cannot be found in your list.", taskIdx + 1));
        }
    }

    /**
     * Removes the task at the specified index of this list.
     *
     * @param taskIdx The index of the task to be removed.
     * @return The task previously at the specified index of this list.
     * @throws TaskListIndexOutOfBoundsException If the taskIdx argument is out of range (taskIdx < 0 ||
     *                                           taskIdx >= size()).
     */
    public Task removeTask(int taskIdx) throws TaskListIndexOutOfBoundsException {
        try {
            return tasks.remove(taskIdx);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException(
                    String.format("☹ OOPS!!! The task of index %d cannot be found in your list.", taskIdx + 1));
        }
    }

    /**
     * Returns the size of this list.
     *
     * @return The size of this list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the string representation of this list.
     *
     * @return The string representation of this list.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, n = tasks.size(); i < n; i++) {
            stringBuilder
                    .append("\t ")
                    .append(i + 1)
                    .append(".")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return stringBuilder.toString().stripTrailing();
    }
}
