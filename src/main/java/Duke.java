import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A Personal Assistant Chatbot that helps the user to keep track of various things.
 */
public class Duke {
    private static final String SAVE_FILE_PATH = "data/duke.txt";

    /**
     * Horizontal line.
     */
    private static final String HOR_LINE = "\t____________________________________________________________";

    private static final String INDEX_ERROR_MSG = "☹ OOPS!!! This command should only have a valid task index as the "
            + "argument.";
    private static final String DESCRIPTION_ERROR_MSG = "☹ OOPS!!! You forgot to include a description.";
    private static final String TIME_ERROR_MSG = "☹ OOPS!!! You forgot to include a date/time.";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    /**
     * List of recorded tasks for the program.
     */
    private List<Task> taskList;

    private Storage storage;

    /**
     * Entry point to run the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Constructs a new Duke instance.
     */
    public Duke() {
        storage = new Storage(SAVE_FILE_PATH);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        // Greet user.
        print(
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "",
                "Hello! I'm Duke.",
                "What can I do for you?"
        );

        // Load tasks from save file.
        try {
            taskList = storage.loadTasks();
        } catch (LoadSavedTasksException e) {
            print(e.getMessages());
            taskList = new ArrayList<>();
        }

        // Start main program.
        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine().strip();
        while (!inputCommand.equals("bye")) {
            try {
                handleInputCommand(inputCommand);
            } catch (DukeException e) {
                print(e.getMessages());
            }

            // Save tasks to save file.
            storage.saveTasks(taskList);

            // Get new input command from user.
            inputCommand = scanner.nextLine().strip();
        }
        scanner.close();

        // Exit message.
        print("Bye. Hope to see you again soon!");
    }

    private void handleInputCommand(String inputCommand) throws DukeException {
        String[] tokens = inputCommand.split("\\s+");

        if (inputCommand.startsWith("done")) {
            // Mark a task as done.
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new InvalidArgumentException("done", INDEX_ERROR_MSG);
            }
            try {
                int taskIdx = Integer.parseInt(tokens[1]) - 1; // May throw NumberFormatException
                markTaskAsDone(taskIdx);
            } catch (NumberFormatException e) {
                // Command is invalid if the argument is not a valid integer.
                throw new InvalidArgumentException("done", INDEX_ERROR_MSG);
            }
        } else if (inputCommand.startsWith("todo")
                || inputCommand.startsWith("deadline")
                || inputCommand.startsWith("event")) {
            // Add a task to the list.
            Task task = parseTaskFromTokens(tokens);
            addTask(task);
        } else if (inputCommand.startsWith("delete")) {
            // Remove a task from the list.
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new InvalidArgumentException("delete", INDEX_ERROR_MSG);
            }
            try {
                int taskIdx = Integer.parseInt(tokens[1]) - 1; // May throw NumberFormatException
                removeTask(taskIdx);
            } catch (NumberFormatException e) {
                // Command is invalid if the argument is not a valid integer.
                throw new InvalidArgumentException("delete", INDEX_ERROR_MSG);
            }
        } else if (inputCommand.equals("list")) {
            // Print the list of tasks.
            printTaskList();
        } else {
            // Invalid commands
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means.");
        }
    }

    private Task parseTaskFromTokens(String[] tokens) throws IncompleteCommandException, DateTimeFormatException {
        String keyword = tokens[0];
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder timeBuilder = new StringBuilder();
        boolean isDescDone = false;
        String delimiter;
        switch (keyword) {
        case "deadline":
            delimiter = "/by";
            break;
        case "event":
            delimiter = "/at";
            break;
        default:
            delimiter = "";
        }

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(delimiter)) {
                isDescDone = true;
            } else if (isDescDone) {
                timeBuilder.append(tokens[i]).append(" ");
            } else {
                descriptionBuilder.append(tokens[i]).append(" ");
            }
        }

        String description = descriptionBuilder.toString().stripTrailing();
        String timeString = timeBuilder.toString().stripTrailing();

        switch (keyword) {
        case "todo":
            if (description.isBlank()) {
                throw new IncompleteCommandException(keyword, DESCRIPTION_ERROR_MSG);
            }
            return new Todo(description);
        case "deadline":
            if (description.isBlank()) {
                throw new IncompleteCommandException(keyword, DESCRIPTION_ERROR_MSG);
            } else if (timeString.isBlank()) {
                throw new IncompleteCommandException(keyword, TIME_ERROR_MSG);
            }

            try {
                LocalDateTime time = LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
                return new Deadline(description, time);
            } catch (DateTimeParseException e) {
                throw new DateTimeFormatException();
            }
        case "event":
            if (description.isBlank()) {
                throw new IncompleteCommandException(keyword, DESCRIPTION_ERROR_MSG);
            } else if (timeString.isBlank()) {
                throw new IncompleteCommandException(keyword, TIME_ERROR_MSG);
            }

            try {
                LocalDateTime time = LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
                return new Event(description, time);
            } catch (DateTimeParseException e) {
                throw new DateTimeFormatException();
            }
        default: // Never reached
            throw new UnknownError("An unexpected error occurred.");
        }
    }

    private void markTaskAsDone(int taskIdx) throws InvalidArgumentException {
        try {
            Task task = taskList.get(taskIdx);
            task.markAsDone();
            print(
                    "Nice! I've marked this task as done: ",
                    String.format("  %s", task)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "done",
                    String.format("☹ OOPS!!! The task of index %d cannot be found in your list.", taskIdx + 1));
        }
    }

    private void addTask(Task task) {
        taskList.add(task);
        print(
                "Got it. I've added this task: ",
                String.format("  %s", task),
                String.format("Now you have %d tasks in the list.", taskList.size())
        );
    }

    private void removeTask(int taskIdx) throws InvalidArgumentException {
        try {
            Task task = taskList.remove(taskIdx);
            print(
                    "Noted. I've removed this task: ",
                    String.format("  %s", task),
                    String.format("Now you have %d tasks in the list.", taskList.size())
            );
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "delete",
                    String.format("☹ OOPS!!! The task of index %d cannot be found in your list.", taskIdx + 1));
        }
    }

    private void printTaskList() {
        int listSize = taskList.size();
        if (listSize == 0) {
            print("There are currently no tasks in your list.");
        } else {
            String[] strings = new String[listSize + 1];
            strings[0] = "Here are the tasks in your list: ";
            for (int i = 0; i < listSize; i++) {
                strings[i + 1] = String.format("%d.%s", i + 1, taskList.get(i));
            }
            print(strings);
        }

    }

    private void print(String... strings) {
        System.out.println(HOR_LINE);
        Arrays.stream(strings).map(string -> String.format("\t %s", string)).forEach(System.out::println);
        System.out.println(HOR_LINE);
        System.out.println();
    }
}
