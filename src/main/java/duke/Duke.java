package duke;

import java.util.LinkedList;
import java.util.NoSuchElementException;

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
 * Entry point of the application Duke.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private TextUi ui;
    private boolean hasGui;
    private LinkedList<Command> commandHistory;

    /**
     * Entry point to run the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Constructs a new Duke instance .
     */
    public Duke() {
        // Set up the text UI.
        ui = new TextUi();

        commandHistory = new LinkedList<>();
    }

    /**
     * Runs the main program of Duke until termination.
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void run() {
        ui.printNormalMessages(Messages.WELCOME_MSG);

        try {
            initStorage();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.printErrorMessages(e.getMessages());
            forceExit();
        }

        Command command;
        do {
            try {
                command = CommandParser.parse(ui.getCommandFromUser());
                ui.printNormalMessages(executeSingleCommand(command));
            } catch (StorageOperationException | IncorrectCommandException e) {
                command = Command.generateNonExitCommand();
                ui.printErrorMessages(e.getMessages());
            }
        } while (!command.isExitCommand());
    }

    /**
     * Executes a single input command from the user and return the response messages.
     *
     * @param command The input command from the user.
     * @return The response messages.
     */
    public String[] executeSingleCommand(Command command) throws IncorrectCommandException, StorageOperationException {
        CommandResponse commandResponse;
        if (command.isUndoCommand()) {
            // Pop the last command and undo
            try {
                commandResponse = commandHistory.pop().undo(taskList);
            } catch (NoSuchElementException e) {
                throw new IncorrectCommandException("Nothing to undo");
            }
        } else {
            commandResponse = command.execute(taskList);
            if (command.canBeUndone()) {
                commandHistory.push(command);
            }
        }
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
     * Forces the application to exit.
     */
    private void forceExit() {
        ui.printErrorMessages("Forced to exit...");
        System.exit(1);
    }
}
