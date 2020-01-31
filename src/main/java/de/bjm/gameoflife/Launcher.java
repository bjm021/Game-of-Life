package de.bjm.gameoflife;

import de.bjm.gameoflife.gui.MainController;
import de.bjm.gameoflife.gui.MainUI;
import de.bjm.gameoflife.gui.options.OptionsUI;
import de.bjm.gameoflife.gui.stats.StatsUI;
import de.bjm.gameoflife.logic.GameOfLife;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

/**
 * Conway's Game of Life rewritten in JavaFX
 *
 *
 * @author b.jm021
 * @version 1.0
 *
 * Launcher Class
 * The entry point to run the Application
 */
public class Launcher extends Application {

    private Stage mainStage;
    private MainUI runningMainUI;
    private GameOfLife game;
    private static Launcher instance;
    private RunningGame runningGame;
    private StatsUI statsUI;
    private Runnable gameLogic;


    //ui elements
    private double scale;
    private OptionsUI optionsUI;


    public Stage getMainStage() {
        return mainStage;
    }

    public GameOfLife getGame() {
        return game;
    }

    public MainUI getRunningMainUI() {
        return runningMainUI;
    }

    public static Launcher getInstance() {
        return instance;
    }

    public OptionsUI getOptionsUI() {
        return optionsUI;
    }

    public double getScale() {
        return scale;
    }

    public StatsUI getStatsUI() {
        return statsUI;
    }

    public Runnable getGameLogic() {
        return gameLogic;
    }

    public void setScale(double scale) {
        this.scale = scale;
        getRunningMainUI().getRunningController().getMainPane().setPrefWidth(100 * scale);
        getRunningMainUI().getRunningController().getMainPane().setPrefHeight(100 * scale);

        getRunningMainUI().getRunningController().getGame().setHeight(100 * scale);
        getRunningMainUI().getRunningController().getGame().setWidth(100 * scale);

        mainStage.setWidth(100 * scale);
        mainStage.setHeight(100 * scale);


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new GameOfLife();
        scale = 5;
        instance = this;
        mainStage = primaryStage;

        runningMainUI = new MainUI(mainStage);
        //mainStage.setWidth(1000);
        //mainStage.setHeight(1000);

        //launching ui parts
        optionsUI = new OptionsUI();
        optionsUI.show();

        statsUI = new StatsUI();
        statsUI.show();


        mainStage.setWidth(100 * scale);
        mainStage.setHeight(100 * scale);
        mainStage.setResizable(false);

        mainStage.show();


        runningGame = new RunningGame();
        //runningGame.start();


    }

    public void startGame() {
        runningGame = new RunningGame();
        runningGame.start();
    }

    public void stopGame() {
        runningGame.stop();
    }
}


class RunningGame extends Thread {

    Runnable gameLogic = new Runnable() {
        @Override
        public void run() {



            MainController controller = Launcher.getInstance().getRunningMainUI().getRunningController();
            GameOfLife game = Launcher.getInstance().getGame();


            game.berechneNaechsteGeneration();
            controller.drawField(Launcher.getInstance().getGame().getFeld());
            Launcher.getInstance().getStatsUI().getController().addData(game.getStep(), game.berechneLebende());


        }
    };

    @Override
    public void run() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {

            try {
                //System.out.println(Launcher.getInstance().getOptionsUI().getController());
                TimeUnit.MILLISECONDS.sleep((long) Launcher.getInstance().getOptionsUI().getController().getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(gameLogic);
        }
    }
}
