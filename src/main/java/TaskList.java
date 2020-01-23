import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<String> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, n = tasks.size(); i < n; i++) {
            stringBuilder
                    .append("\t ")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return stringBuilder.toString().stripTrailing();
    }
}
