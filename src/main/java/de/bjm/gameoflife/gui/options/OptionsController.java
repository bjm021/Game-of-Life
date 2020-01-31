package de.bjm.gameoflife.gui.options;

import de.bjm.gameoflife.Launcher;
import de.bjm.gameoflife.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class OptionsController {

    @FXML
    AnchorPane mainPane;

    @FXML
    Slider speedSlider, scaleSlider;

    @FXML
    Button gameStartButton, gameStopButton;

    @FXML
    CheckBox editModeCheckBox, darkModeCheckBox;

    @FXML
    CheckMenuItem statsCheckMenu, outputCheckMenu;

    public CheckMenuItem getStatsCheckMenu() {
        return statsCheckMenu;
    }
    public CheckMenuItem getOutputCheckMenu() {
        return outputCheckMenu;
    }

    @FXML
    public void initialize() {
        // check theme
        //String theme = Config.readValue(Config.ConfigValue.UI_THEME);
        //if (theme.equalsIgnoreCase("dark")) {
        //    // TODO: 26/01/2020 set dark css to elements
        //
        //} else if (theme.equalsIgnoreCase("light")) {
        //    // TODO: 26/01/2020 light theme
       ///}


        scaleSlider.setSnapToTicks(true);
        scaleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Launcher.getInstance().setScale(newValue.doubleValue());
            Launcher.getInstance().getRunningMainUI().getRunningController().drawGrid();
            Launcher.getInstance().getRunningMainUI().getRunningController().drawField(Launcher.getInstance().getGame().getFeld());
        });

        editModeCheckBox.setOnAction(event -> {
            Launcher.getInstance().getRunningMainUI().getRunningController().setEditMode(editModeCheckBox.isSelected());
        });

        darkModeCheckBox.setOnAction(event -> {
            if (darkModeCheckBox.isSelected()) {
                Launcher.getInstance().getRunningMainUI().getRunningController().setTheme(MainController.CSSTheme.DARK);
            } else {
                Launcher.getInstance().getRunningMainUI().getRunningController().setTheme(MainController.CSSTheme.LIGHT);
            }
        });



        statsCheckMenu.setOnAction(event -> {
            if (statsCheckMenu.isSelected()) {
                Launcher.getInstance().getStatsUI().show();
            } else {
                Launcher.getInstance().getStatsUI().hide();
            }
        });

        outputCheckMenu.setOnAction(event -> {
            if (outputCheckMenu.isSelected()) {
                Launcher.getInstance().getMainStage().show();
            } else {
                Launcher.getInstance().getMainStage().hide();
            }
        });

        gameStopButton.setDisable(true);
    }

    public double getSpeed() {
        return speedSlider.getValue();
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    @FXML
    private void startGame() {
        Launcher.getInstance().startGame();
        gameStartButton.setDisable(true);
        gameStopButton.setDisable(false);
    }

    @FXML
    private void stopGame() {
        Launcher.getInstance().stopGame();
        gameStopButton.setDisable(true);
        gameStartButton.setDisable(false);
    }

    @FXML
    private void resetField() {
        Launcher.getInstance().getGame().resetGame();
        Launcher.getInstance().getRunningMainUI().getRunningController().drawField(Launcher.getInstance().getGame().getFeld());
        Launcher.getInstance().getStatsUI().getController().resetGraph();
    }

    @FXML
    private void resetSystem() {
        Launcher.getInstance().getRunningMainUI().getRunningController().resetSystem();
    }

}
