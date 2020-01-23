import java.util.Scanner;

/**
 * A Personal Assistant Chatbot that helps the user to keep track of various things.
 */
public class Duke {
    /** Duke logo. */
    private static final String logo = "\t  ____        _        \n"
            + "\t |  _ \\ _   _| | _____ \n"
            + "\t | | | | | | | |/ / _ \\\n"
            + "\t | |_| | |_| |   <  __/\n"
            + "\t |____/ \\__,_|_|\\_\\___|\n";

    /** Horizontal line. */
    private static final String horLine = "\t____________________________________________________________";

    /** List of recorded tasks for the program. */
    private static  TaskList taskList = new TaskList();

    /**
     * Runs Duke.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        greetUser();
        runProgram();
    }

    private static void greetUser() {
        System.out.printf(
                "%s\n"
                + "%s\n"
                + "\t Hello! I'm Duke.\n"
                + "\t What can I do for you?\n"
                + "%s\n\n",
                horLine, logo, horLine);
    }

    private static void runProgram() {
        Scanner scanner = new Scanner(System.in);

        String inputCommand = scanner.nextLine().trim();
        while (!inputCommand.equals("bye")) {
            System.out.println(horLine);
            try {
                handleInputCommand(inputCommand);
            } catch (DukeException e) {
                System.out.printf("\t %s\n", e.getMessage());
            }
            System.out.printf("%s\n\n", horLine);
            inputCommand = scanner.nextLine().trim();
        }

        System.out.printf(
                "%s\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "%s\n",
                horLine, horLine);
        scanner.close();
    }

    private static void handleInputCommand(String inputCommand) throws DukeException {
        String[] tokens = inputCommand.split("\\s+");
        String keyword = tokens[0];

        if (keyword.equals("done")) {
            // Mark a task as done.
            if (tokens.length != 2) {
                throw new InvalidCommandException(
                        "☹ OOPS!!! The done command should only have the task index as an argument.");
            }

            try {
                int taskIdx = Integer.parseInt(tokens[1]) - 1;
                handleMarkTaskAsDone(taskIdx);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(
                        "☹ OOPS!!! The done command should only have the task index as an argument.");
            }
        } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
            // Add a task to the list.
            Task task = parseTaskFromCommand(keyword, tokens);
            handleAddTask(task);
        } else if (inputCommand.equals("list")) {
            // Print the list of tasks.
            handlePrintTaskList();
        } else {
            // Invalid commands
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Task parseTaskFromCommand(String keyword, String[] tokens) throws InvalidCommandException {
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
        String time = timeBuilder.toString().stripTrailing();

        switch (keyword) {
        case "deadline":
            if (description.isBlank()) {
                throw new InvalidCommandException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (time.isBlank()) {
                throw new InvalidCommandException(
                        "☹ OOPS!!! The time of a deadline cannot be empty. Add the time by adding \"/by [time]\"");
            }
            return new Deadline(description, time);
        case "event":
            if (description.isBlank()) {
                throw new InvalidCommandException("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (time.isBlank()) {
                throw new InvalidCommandException(
                        "☹ OOPS!!! The time of an event cannot be empty. Add the time by adding \"/at [time]\"");
            }
            return new Event(description, time);
        default:
            if (description.isBlank()) {
                throw new InvalidCommandException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new Todo(description);
        }
    }

    private static void handleMarkTaskAsDone(int taskIdx) throws TaskListIndexOutOfBoundsException {
        Task task = taskList.getTask(taskIdx);
        task.markAsDone();
        System.out.printf(
                "\t Nice! I've marked this task as done: \n"
                + "\t   %s\n",
                task);
    }

    private static void handleAddTask(Task task) {
        taskList.addTask(task);
        System.out.printf(
                "\t Got it. I've added this task: \n"
                + "\t   %s\n"
                + "\t Now you have %d tasks in the list.\n",
                task, taskList.size());
    }

    private static void handlePrintTaskList() {
        if (taskList.size() == 0) {
            System.out.println("\t There are currently no tasks in your list.");
        } else {
            System.out.println("\t Here are the tasks in your list: ");
            System.out.println(taskList);
        }

    }
}
