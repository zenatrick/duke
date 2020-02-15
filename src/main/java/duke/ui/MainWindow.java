package duke.ui;

import duke.Duke;
import duke.exception.IncorrectCommandException;
import duke.exception.InvalidStorageFilePathException;
import duke.exception.StorageOperationException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static duke.common.Messages.WELCOME_MSG;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the duke application instance and the window instance of this application.
     *
     * @param d The duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        showMessagesToUser(WELCOME_MSG);
        try {
            duke.initStorage();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            showMessagesToUser(e.getMessages());
            Platform.exit();
            System.exit(1);
        }
    }

    public void showMessagesToUser(String... messages) {
        dialogContainer.getChildren().add(
                new DukeDialogBox(String.join("\n", messages)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] response = getResponse(input);
        dialogContainer.getChildren().add(new UserDialogBox(input));
        showMessagesToUser(response);
        userInput.clear();
    }

    private String[] getResponse(String input) {
        try {
            return duke.parseAndExecuteSingleCommand(input);
        } catch (StorageOperationException | IncorrectCommandException e) {
            return e.getMessages();
        }
    }

}
