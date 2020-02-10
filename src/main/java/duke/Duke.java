package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.command.CommandResponse;
import duke.exception.IncorrectCommandException;
import duke.exception.InvalidStorageFilePathException;
import duke.exception.StorageOperationException;
import duke.ui.UiManager;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Initializes the application and starts the interaction with the user.
 */
public class Duke extends Application {
    private TaskList taskList;
    private Storage storage;
    private UiManager ui;

    /**
     * Constructs a new Duke instance .
     */
    public Duke() {
        // Set up the UI.
        ui = new UiManager();
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage, this);
    }

    /**
     * Executes a single input command from the user and return the response messages.
     *
     * @param input The input command from the user.
     * @return The response messages.
     */
    public String[] parseAndExecuteSingleCommand(String input) throws IncorrectCommandException, StorageOperationException {
        Command command = CommandParser.parse(input);
        if (command.isExitCommand()) {
            exit();
        }
        CommandResponse commandResponse = command.execute(taskList);
        storage.saveTaskListToStorage(taskList);
        return commandResponse.get();
    }

    /**
     * Initializes the storage for the application.
     */
    public void initStorage() throws InvalidStorageFilePathException, StorageOperationException {
        // Load task list from storage file.
        storage = new Storage();
        taskList = storage.loadTaskListFromStorage();
    }

    /**
     * Exits the application.
     */
    private void exit() {
        Platform.exit();
        System.exit(0);
    }
}
