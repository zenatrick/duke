package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {

    private static final String LINE_PREFIX = "\t ";
    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a new TextUI instance with the System.in as the InputStream and System.out as the PrintStream.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new TextUI instance with the specified InputStream and the specified PrintStream.
     */
    public TextUi(InputStream inputStream, PrintStream outputStream) {
        in = new Scanner(inputStream);
        out = outputStream;
    }

    /**
     * Returns the full line of input command from the user.
     *
     * @return The full line of input command from the user.
     */
    public String getCommandFromUser() {
        return in.nextLine().strip();
    }

    /**
     * Prints specified list of error messages to the console.
     *
     * @param messages The list of error messages to be printed.
     */
    public void printErrorMessages(String... messages) {
        out.println("\t" + DIVIDER);
        out.println(LINE_PREFIX + "\u2639 OOPS!!!"); // Prints "☹ OOPS!!!"
        printToConsole(messages);
        out.println("\t" + DIVIDER);
        out.println();
    }

    /**
     * Prints specified list of normal messages to the console.
     *
     * @param messages The list of normal messages to be printed.
     */
    public void printNormalMessages(String... messages) {
        out.println("\t" + DIVIDER);
        printToConsole(messages);
        out.println("\t" + DIVIDER);
        out.println();
    }

    private void printToConsole(String... messages) {
        Arrays.stream(messages).map(message -> String.format("%s%s", LINE_PREFIX, message)).forEach(out::println);
    }
}
