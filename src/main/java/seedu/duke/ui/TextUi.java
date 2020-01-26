package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class TextUi {

    private static final String LINE_PREFIX = "\t ";
    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream inputStream, PrintStream outputStream) {
        in = new Scanner(inputStream);
        out = outputStream;
    }

    public String getCommandFromUser() {
        return in.nextLine().strip();
    }

    public void printErrorMessages(String... messages) {
        printDivider();
        printToConsole("\u2639 OOPS!!!");
        printToConsole(messages);
        printDivider();
        out.println();
    }

    public void printNormalMessages(String... messages) {
        printDivider();
        printToConsole(messages);
        printDivider();
        out.println();
    }

    private void printToConsole(String... messages) {
        Arrays.stream(messages).map(message -> String.format("%s%s", LINE_PREFIX, message)).forEach(out::println);
    }

    private void printDivider() {
        out.println("\t" + DIVIDER);
    }
}
