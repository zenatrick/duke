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
            handleInputCommand(inputCommand);
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

    private static void handleInputCommand(String inputCommand) {
        String[] tokens = inputCommand.split("\\s+");
        String keyword = tokens[0];

        if (keyword.equals("done")) {
            // Mark a task as done.
            int taskIdx = Integer.parseInt(tokens[1]) - 1; // assume tokens has a length of 2
            handleMarkTaskAsDone(taskIdx);
        } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
            // Add a task to the list.
            Task task = parseTaskFromCommand(keyword, tokens);
            handleAddTask(task);
        } else if (inputCommand.equals("list")) {
            // Print the list of tasks.
            handlePrintTaskList();
        } else {
            // Temporary error handling.
            System.out.println("\t No such command");
        }
    }

    private static Task parseTaskFromCommand(String keyword, String[] tokens) {
        // Assumes command given is valid.
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
            return new Deadline(description, time);
        case "event":
            return new Event(description, time);
        default:
            return new Todo(description);
        }
    }

    private static void handleMarkTaskAsDone(int taskIdx) {
        Task task = taskList.getTask(taskIdx); // assume taskIdx is not out of bound
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
                task, taskList.getSize());
    }

    private static void handlePrintTaskList() {
        System.out.println("\t Here are the tasks in your list: ");
        System.out.println(taskList);
    }
}
