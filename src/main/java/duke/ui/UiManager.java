package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UiManager {
    /**
     * Sets up and starts the GUI for the Duke application.
     * @param primaryStage The primary stage of the application.
     * @param duke The Duke instance to run.
     */
    public void start(Stage primaryStage, Duke duke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
