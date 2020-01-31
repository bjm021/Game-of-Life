package de.bjm.gameoflife.gui.stats;

import de.bjm.gameoflife.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StatsUI {

    private Stage statsStage;

    private FXMLLoader statsLoader;

    private Scene stageScene;

    private StatsController controller;

    public StatsController getController() {
        return controller;
    }

    public StatsUI() {
        statsStage = new Stage();
        statsLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/stats.fxml"));
        try {
            Parent root = statsLoader.load();
            controller = statsLoader.getController();
            stageScene = new Scene(root);
            statsStage.setScene(stageScene);

            statsStage.setX(200);
            statsStage.setResizable(false);
            statsStage.setTitle("Statistics & Analytics");

            statsStage.setOnCloseRequest(event -> {
                Launcher.getInstance().getOptionsUI().getController().getStatsCheckMenu().setSelected(false);
            });

            statsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void show() {
        statsStage.show();
    }

    public void hide() {
        statsStage.hide();
    }

}
