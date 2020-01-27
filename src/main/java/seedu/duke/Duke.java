package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.command.CommandResponse;
import seedu.duke.common.Messages;
import seedu.duke.exception.IncorrectCommandException;
import seedu.duke.exception.InvalidStorageFilePathException;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.TextUi;

/**
 * Entry point of the application Duke.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private static final String STORAGE_FILE_PATH_TEMP = "data/duke.txt";

    private TaskList taskList;
    private Storage storage;
    private TextUi ui;

    /**
     * Entry point to run the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Constructs a new Duke instance.
     * Sets up the required objects, prints the welcome message and loads the data from the storage file.
     */
    public Duke() {
        // Set up the text UI.
        ui = new TextUi();

        // Print welcome message.
        ui.printNormalMessages(Messages.WELCOME_MSG);

        // Load task list from storage file.
        try {
            storage = new Storage(STORAGE_FILE_PATH_TEMP);
            taskList = storage.loadTaskListFromStorage();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.printErrorMessages(e.getMessages());
            forceExit();
        }
    }

    /**
     * Runs the main program of Duke until termination.
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void run() {
        Command command;
        do {
            try {
                command = CommandParser.parse(ui.getCommandFromUser());
                CommandResponse commandResponse = command.execute(taskList);
                ui.printNormalMessages(commandResponse.get());
                storage.saveTaskListToStorage(taskList);
            } catch (StorageOperationException | IncorrectCommandException e) {
                command = Command.generateNonExitCommand();
                ui.printErrorMessages(e.getMessages());
            }
        } while (!command.isExitCommand());
    }

    /**
     * Force the application to exit.
     */
    private void forceExit() {
        ui.printErrorMessages("Forced to exit...");
        System.exit(0);
    }
}
