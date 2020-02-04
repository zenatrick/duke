package duke.gui;

import duke.Duke;
import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.IncorrectCommandException;
import duke.exception.InvalidStorageFilePathException;
import duke.exception.StorageOperationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Stage window;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the duke application instance and the window instance of this application.
     *
     * @param d     The duke instance.
     * @param stage The window instance.
     */
    public void setDukeAndWindow(Duke d, Stage stage) {
        window = stage;
        duke = d;
        showMessagesToUser(WELCOME_MSG);
        try {
            duke.initStorage();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            showMessagesToUser(e.getMessages());
            window.close();
        }
    }

    public void showMessagesToUser(String... messages) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(String.join("\n", messages), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] response = getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        showMessagesToUser(response);
        userInput.clear();
    }

    private String[] getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            if (command.isExitCommand()) {
                window.close();
            }
            return duke.executeSingleCommand(command);
        } catch (StorageOperationException | IncorrectCommandException e) {
            return e.getMessages();
        }
    }

}
