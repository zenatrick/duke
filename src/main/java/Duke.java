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

    /**
     * Runs Duke.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        greetUser();
        runProgram();
    }

    private static void greetUser() {
        System.out.format(
                "%s\n%s\n\t Hello! I'm Duke.\n\t What can I do for you?\n%s\n\n",
                horLine, logo, horLine
        );
    }

    private static void runProgram() {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            System.out.println(horLine);

            String[] tokens = input.split("\\s+");
            if (tokens[0].equals("done")) {
                int taskIdx = Integer.parseInt(tokens[1]) - 1; // assume tokens has a length of 2
                Task task = taskList.getTask(taskIdx); // assume taskIdx is not out of bound
                task.markAsDone();
                System.out.format("\t Nice! I've marked this task as done:\n\t   %s\n", task);
            } else if (input.equals("list")) {
                System.out.println("\t Here are the tasks in your list: ");
                System.out.println(taskList);
            } else {
                taskList.addTask(new Task(input));
                System.out.format("\t added: %s\n", input);
            }

            System.out.format("%s\n\n", horLine);

            input = scanner.nextLine().trim();
        }

        System.out.format("%s\n\t Bye. Hope to see you again soon!\n%s\n", horLine, horLine);

        scanner.close();
    }
}
