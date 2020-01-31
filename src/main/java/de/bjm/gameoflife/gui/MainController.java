package de.bjm.gameoflife.gui;

import de.bjm.gameoflife.Launcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    public enum CSSTheme {
        DARK, LIGHT
    }


    @FXML
    AnchorPane mainPane;

    @FXML
    Canvas game;
    GraphicsContext gc;

    private boolean editMode = false;

    private CSSTheme currentTheme;

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Canvas getGame() {
        return game;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    List<Line> activeGrid = new ArrayList<>();

    public void drawGrid() {
        double scale = Launcher.getInstance().getScale();

        mainPane.getChildren().removeAll(activeGrid);
        activeGrid.clear();

        Color fillColor;
        if (currentTheme == CSSTheme.DARK) {
            fillColor = Color.WHITE;
        } else if (currentTheme == CSSTheme.LIGHT) {
            fillColor = Color.LIGHTGRAY;
        } else {
            fillColor = Color.LIGHTGRAY;
        }

        for (double i = 0; i < 100*scale; i = i + scale) {
            Line tmpLineX = LineBuilder.create()
                    .startX(i)
                    .startY(0)
                    .endX(i)
                    .endY(100*scale)
                    .stroke(fillColor)
                    .strokeWidth(1)
                    .build();

            Line tmpLineY = LineBuilder.create()
                    .startY(i)
                    .startX(0)
                    .endY(i)
                    .endX(100*scale)
                    .stroke(fillColor)
                    .strokeWidth(1)
                    .build();

            mainPane.getChildren().add(tmpLineX);
            mainPane.getChildren().add(tmpLineY);
            activeGrid.add(tmpLineX);
            activeGrid.add(tmpLineY);
        }




    }

    @FXML
    public void initialize() {

        //mainPane.setStyle("-fx-background-color: #222222;");
        drawGrid();

        gc = game.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.setStroke(Color.RED);
        //gc.fillRect(0, 0, 10, 10);

        drawField(Launcher.getInstance().getGame().getFeld());


        mainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            double scale = Launcher.getInstance().getScale();
            System.out.println(event.getX() + ":" + event.getY());

            //set field id edit
            if(editMode) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                //check if teiler von 5
                while (x % 5 != 0) {
                    x--;
                }
                while (y % 5 != 0) {
                    y--;
                }

                x = x / (int) scale;
                y = y / (int) scale;

                byte[][] feld = Launcher.getInstance().getGame().getFeld();

                if (feld[y][x] == 0) {
                    feld[y][x] = 1;
                } else {
                    feld[y][x] = 0;
                }
                drawField(Launcher.getInstance().getGame().getFeld());


                System.out.println("New Coordinates (div by " + scale + "): " + x + " " + y);


            }
        });


    }



    public void drawField(byte[][] field) {
        double scale = Launcher.getInstance().getScale();

        gc.clearRect(0, 0, game.getWidth(), game.getHeight());
        for (int i = 1; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (field[i][j] == 1) {
                    gc.fillRect(j*scale, i*scale, 1*scale, 1*scale);
                }
            }
        }

    }

    public void setTheme(CSSTheme theme) {
        if (theme == CSSTheme.DARK) {
            // TODO: 26/01/2020 set dark css to elements
            currentTheme = CSSTheme.DARK;
            gc.setFill(Color.WHITE);
            mainPane.setStyle("-fx-background-color: #000000;");
        } else if (theme == CSSTheme.LIGHT) {
            // TODO: 26/01/2020 light theme
            currentTheme = CSSTheme.LIGHT;
            gc.setFill(Color.BLACK);
            mainPane.setStyle("-fx-background-color: #ffffff;");
        }
        drawGrid();
        drawField(Launcher.getInstance().getGame().getFeld());
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    public void resetSystem() {
        Launcher.getInstance().stopGame();
        game = new Canvas();
        game.setHeight(1000);
        game.setWidth(1000);
        gc = game.getGraphicsContext2D();
        drawGrid();
        drawField(Launcher.getInstance().getGame().getFeld());
        Launcher.getInstance().startGame();
    }



}
