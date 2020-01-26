package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.command.CommandResponse;
import duke.common.Messages;
import duke.exception.IncorrectCommandException;
import duke.exception.InvalidStorageFilePathException;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A Personal Assistant Chatbot that helps the user to keep track of various things.
 */
public class Duke {
    private static final String STORAGE_FILE_PATH_TEMP = "data/duke.txt";

    private TaskList taskList;
    private Storage storage;
    private TextUi ui;

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
        ui = new TextUi();

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
     * Runs the main program of Duke.
     */
    public void run() {
        runCommandLoopUntilExitCommand();
    }

    private void forceExit() {
        ui.printErrorMessages("Forced to exit...");
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
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
}
