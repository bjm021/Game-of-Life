package de.bjm.gameoflife.gui.options;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class OptionsUI {

    private Stage mainStage;
    private FXMLLoader loader;
    private OptionsController controller;

    public OptionsController getController() {
        return controller;
    }
    public Stage getMainStage() {
        return mainStage;
    }

    public OptionsUI() {
        mainStage = new Stage();
        loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/options.fxml"));



        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            controller = loader.getController();
            mainStage.setScene(scene);
            mainStage.setX(500);
            mainStage.setResizable(false);
            mainStage.setTitle("Options & Settings");

            mainStage.setOnCloseRequest(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Close the Game?");
                alert.setHeaderText("By closing thi s window you will completely quit the game! All progress that's not saved will be lost!");
                alert.setContentText("Do you want to continue?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    System.exit(0);
                } else {
                    // ... user chose CANCEL or closed the dialog
                    event.consume();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void show() {
        mainStage.show();
    }

    public void hide() {
        mainStage.hide();
    }

}
