package seedu.duke.common;

/**
 * Container and generator for user visible messages.
 */
public class Messages {
    /**
     * The welcome message.
     */
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

    /**
     * The goodbye message.
     */
    public static final String[] GOODBYE_MSG = new String[]{
        "Bye. Hope to see you again soon!"
    };

    /**
     * The message displayed when this list of task is empty.
     */
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

    /**
     * The message displayed when the description of a task to be added is not specified.
     */
    public static final String EMPTY_DESCRIPTION_ERROR_MSG = "You forgot to include a description.";

    /**
     * The message displayed when the time of a task to be added is not specified.
     */
    public static final String EMPTY_TIME_ERROR_MSG = "You forgot to include a time.";

    /**
     * The message displayed when the index specified in the user's input command is invalid.
     */
    public static final String INVALID_INDEX_ERROR_MSG = "This command should only have a valid task index as the "
            + "argument.";

    /**
     * The message displayed when the application is unable to decode the storage file data.
     */
    public static final String[] INVALID_ENCODING_MSG = new String[]{
        "Encoded task in your storage file has an invalid format.",
        "I am unable to decode."
    };

    /**
     * The message displayed when the path of the storage file is invalid.
     */
    public static final String INVALID_FILE_MSG = "Storage file must be a text file ending with .txt";

    /**
     * Generates and returns the error message when the application is unable to write to the storage file.
     *
     * @param filePath The path of the storage file.
     * @return The error message.
     */
    public static String[] generateWriteToFileErrorMessage(String filePath) {
        return new String[]{
            "Unable to write to file: ",
            String.format("  %s", filePath)
        };
    }

    /**
     * Generates and returns the error message when the application is unable to read from the storage file.
     *
     * @param filePath The path of the storage file.
     * @return The error message.
     */
    public static String[] generateReadFromFileErrorMessage(String filePath) {
        return new String[]{
            "Error reading data from file: ",
            String.format("  %s", filePath)
        };
    }

    /**
     * Generates and returns the success message upon adding a new task to the list.
     *
     * @param task The Task added.
     * @param size The current size of the TaskList.
     * @return The success message.
     */
    public static String[] generateAddSuccessMessage(String task, int size) {
        return new String[]{
            "Got it. I've added this task: ",
            String.format("  %s", task),
            String.format("Now you have %d tasks in the list.", size)
        };
    }


    /**
     * Generates and returns the error message upon failure to add a Todo task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String[] generateAddTodoErrorMessage(String message) {
        return new String[]{
            message,
            "To add a todo, tell me in this format: ",
            "  todo [description]"
        };
    }

    /**
     * Generates and returns the error message upon failure to add a Deadline task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String[] generateAddDeadlineErrorMessage(String message) {
        return new String[]{
            message,
            "To add a deadline, tell me in this format: ",
            "  todo [description] /by [time]",
            "  Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }

    /**
     * Generates and returns the error message upon failure to add a Event task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String[] generateAddEventErrorMessage(String message) {
        return new String[]{
            message,
            "To add an event, tell me in this format: ",
            "  todo [description] /at [time]",
            "  Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }

    /**
     * Generates and returns the success message upon marking a task as done.
     *
     * @param task The task that is marked as done.
     * @return The success message.
     */
    public static String[] generateDoneSuccessMessage(String task) {
        return new String[]{
            "Nice! I've marked this task as done: ",
            String.format("  %s", task)
        };
    }

    /**
     * Generates and returns the error message upon failure to mark a task as done.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String[] generateDoneErrorMessage(String message) {
        return new String[]{
            message,
            "To mark a task as done, tell me in this format: ",
            "  done [task_index]"
        };
    }

    /**
     * Generates and returns the error message upon failure to parse the user's input time.
     *
     * @return The error message.
     */
    public static String[] generateTimeFormatErrorMessage() {
        return new String[]{
            "I don't understand the time entered.",
            "Time should be in the format of 'dd/MM/yyyy HH:mm'"
        };
    }

    /**
     * Generates and returns the success message upon deleting a task.
     *
     * @param task The task that is deleted.
     * @param size The current size of the TaskList.
     * @return The success message.
     */
    public static String[] generateDeleteSuccessMessage(String task, int size) {
        return new String[]{
            "Noted. I've removed this task: ",
            String.format("  %s", task),
            String.format("Now you have %d tasks in the list.", size)
        };
    }

    /**
     * Generates and returns the error message upon failure to delete a task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String[] generateDeleteErrorMessage(String message) {
        return new String[]{
            message,
            "To delete a task, tell me in this format: ",
            "  delete [task_index]"
        };
    }

    /**
     * Generates and returns the error message when the user's input more than one argument for the find command.
     *
     * @return The error message.
     */
    public static String[] generateFindErrorMessage() {
        return new String[]{
            "Too many arguments for this command. ",
            "To find a task, tell me in this format: ",
            " find [keyword]",
            "Only one keyword is allowed at a time."
        };
    }

    /**
     * Generates and returns the error message when the user's input task index is not found in the list.
     *
     * @param taskIndex The user's input task index that is invalid.
     * @return The error message.
     */
    public static String[] generateTaskIndexNotFoundMessage(int taskIndex) {
        return new String[]{
            String.format("The task of index %d cannot be found in your list.", taskIndex)
        };
    }
}
