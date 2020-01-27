package seedu.duke.common;

public class Messages {
    public static final String[] WELCOME_MSG = new String[]{
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|",
        "",
        "Hello! I'm Duke.",
        "What can I do for you?"
    };
    public static final String[] GOODBYE_MSG = new String[]{
        "Bye. Hope to see you again soon!"
    };
    public static final String[] EMPTY_TASK_LIST_MSG = new String[]{
        "There are currently no tasks in your list.",
        "Add a task by using one of the following commands: ",
        "  todo [description]",
        "  deadline [description] /by [time]",
        "  event [description] /at [time]",
        "Time should be in the format of 'dd/MM/yyyy HH:mm'"
    };
    public static final String[] EMPTY_FIND_RESULT_MSG = new String[]{
        "There are no matching tasks in your list."
    };

    public static final String EMPTY_DESCRIPTION_ERROR_MSG = "You forgot to include a description.";
    public static final String EMPTY_TIME_ERROR_MSG = "You forgot to include a time.";
    public static final String INVALID_INDEX_ERROR_MSG = "This command should only have a valid task index as the "
            + "argument.";
    public static final String[] INVALID_ENCODING_MSG = new String[]{
        "Encoded task in your storage file has an invalid format.",
        "I am unable to decode."
    };
    public static final String INVALID_FILE_MSG = "Storage file must be a text file ending with .txt";

    public static String[] generateWriteToFileErrorMessage(String filePath) {
        return new String[]{
            "Unable to write to file: ",
            String.format("  %s", filePath)
        };
    }

    public static String[] generateReadFromFileErrorMessage(String filePath) {
        return new String[]{
            "Error reading data from file: ",
            String.format("  %s", filePath)
        };
    }

    public static String[] generateAddSuccessMessage(String task, int size) {
        return new String[]{
            "Got it. I've added this task: ",
            String.format("  %s", task),
            String.format("Now you have %d tasks in the list.", size)
        };
    }


    public static String[] generateAddTodoErrorMessage(String message) {
        return new String[]{
            message,
            "To add a todo, tell me in this format: ",
            "  todo [description]"
        };
    }

    public static String[] generateAddDeadlineErrorMessage(String message) {
        return new String[]{
            message,
            "To add a deadline, tell me in this format: ",
            "  todo [description] /by [time]",
            "  Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }

    public static String[] generateAddEventErrorMessage(String message) {
        return new String[]{
            message,
            "To add an event, tell me in this format: ",
            "  todo [description] /at [time]",
            "  Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }

    public static String[] generateDoneSuccessMessage(String task) {
        return new String[]{
            "Nice! I've marked this task as done: ",
            String.format("  %s", task)
        };
    }

    public static String[] generateDoneErrorMessage(String message) {
        return new String[]{
            message,
            "To mark a task as done, tell me in this format: ",
            "  done [task_index]"
        };
    }

    public static String[] generateTimeFormatErrorMessage() {
        return new String[]{
            "I don't understand the time entered.",
            "Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }

    public static String[] generateDeleteSuccessMessage(String task, int size) {
        return new String[]{
            "Noted. I've removed this task: ",
            String.format("  %s", task),
            String.format("Now you have %d tasks in the list.", size)
        };
    }

    public static String[] generateDeleteErrorMessage(String message) {
        return new String[]{
            message,
            "To delete a task, tell me in this format: ",
            "  delete [task_index]"
        };
    }

    public static String[] generateFindErrorMessage() {
        return new String[]{
            "Too many arguments for this command. ",
            "To find a task, tell me in this format: ",
            " find [keyword]",
            "Only one keyword is allowed at a time."
        };
    }

    public static String[] generateTaskIndexNotFoundMessage(int taskIndex) {
        return new String[]{
            String.format("The task of index %d cannot be found in your list.", taskIndex)
        };
    }
}
