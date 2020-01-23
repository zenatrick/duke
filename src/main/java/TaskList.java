import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int taskIdx) {
        return tasks.get(taskIdx);
    }

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
