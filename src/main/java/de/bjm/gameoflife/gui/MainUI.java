package de.bjm.gameoflife.gui;

import de.bjm.gameoflife.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainUI {

    private FXMLLoader mainLoader;

    private MainController runningController;

    public FXMLLoader getMainLoader() {
        return mainLoader;
    }
    public MainController getRunningController() {
        return runningController;
    }

    public MainUI(Stage mainStage) throws IOException {
        mainLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/mainUI.fxml"));
        Parent root = mainLoader.load();
        Scene mainScene = new Scene(root);
        System.out.println(mainScene.getStylesheets());
        mainStage.setScene(mainScene);

        mainStage.setOnCloseRequest(event -> {
            Launcher.getInstance().getOptionsUI().getController().getOutputCheckMenu().setSelected(false);
        });

        mainStage.setTitle("Game output");
        runningController = mainLoader.getController();

        // check theme
        //String theme = Config.readValue(Config.ConfigValue.UI_THEME);
        //if (theme.equalsIgnoreCase("dark")) {
        //    runningController.setTheme(MainController.CSSTheme.DARK);
        //}
    }


}
