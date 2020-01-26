import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Returns the list of tasks from the storage file. If no file is found, return an empty list.
     *
     * @return The list of tasks from the storage file.
     */
    public List<Task> loadTasks() throws LoadSavedTasksException {
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                List<Task> savedTasks = bufferedReader
                        .lines()
                        .map(str -> {
                            try {
                                return parseTask(str);
                            } catch (UnexpectedTaskFormatException e) {
                                return null;
                            }
                        })
                        .collect(Collectors.toList());
                bufferedReader.close();

                if (savedTasks.stream().anyMatch(Objects::isNull)) {
                    throw new LoadSavedTasksException();
                }
                return savedTasks;
            } catch (IOException e) {
                throw new LoadSavedTasksException();
            }
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Save the specified list of tasks into the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        file.getParentFile().mkdirs();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(tasks.stream()
                    .map(Task::serialize)
                    .collect(Collectors.joining("\n"))
            );
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Task parseTask(String serializedTask) throws UnexpectedTaskFormatException {
        String[] tokens = serializedTask.split(",");

        Task task;
        try {
            String taskType = tokens[0];
            switch (taskType) {
            case "T":
                task = new Todo(tokens[2]);
                break;
            case "D":
                task = new Deadline(tokens[2], tokens[3]);
                break;
            case "E":
                task = new Event(tokens[2], tokens[3]);
                break;
            default:
                throw new UnexpectedTaskFormatException();
            }

            int doneValue = Integer.parseInt(tokens[1]);
            if (doneValue == 1) {
                task.markAsDone();
            } else if (doneValue != 0) {
                throw new UnexpectedTaskFormatException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new UnexpectedTaskFormatException();
        }

        return task;
    }
}
