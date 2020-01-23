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
        System.out.println(
                horLine + "\n"
                + logo + "\n"
                + "\t Hello! I'm Duke.\n"
                + "\t What can I do for you?\n"
                + horLine);
    }

    private static void runProgram() {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println(horLine);

            if (input.equals("list")) {
                System.out.println(taskList);
            } else {
                taskList.addTask(input);
                System.out.println("\t added: " + input);
            }

            System.out.println(horLine);

            input = scanner.nextLine();
        }

        System.out.println(
                horLine + "\n"
                + "\t Bye. Hope to see you again soon!\n"
                + horLine
        );

        scanner.close();
    }
}
