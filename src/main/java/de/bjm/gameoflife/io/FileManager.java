package de.bjm.gameoflife.io;

import de.bjm.gameoflife.Launcher;
import javafx.application.Platform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileManager {

    public boolean saveFile(File saveFile) {

        StringBuilder sb = new StringBuilder();

        byte[][] feld = Launcher.getInstance().getGame().getFeld();
        for (int i = 1; i < 99; i++) {
            for (int j = 1; j < 99; j++) {
                if (feld[i][j] == 1) {
                    sb.append(i);
                    sb.append(":");
                    sb.append(j);
                    sb.append(",");
                }
            }
        }

        String output = sb.toString().substring(0, sb.toString().length() - 1);

        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write(output);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void readFile(File file) {
        //Launcher.getInstance().stopGame();
        Launcher.getInstance().getOptionsUI().getController().stopGame();
        Launcher.getInstance().getOptionsUI().getController().resetField();


        Platform.runLater(() -> {
            try {
                Launcher.getInstance().getGame().clear();
                List<String> lines =  Files.readAllLines(file.toPath());

                String read = lines.get(0);
                String[] fields = read.split(",");

                for (String field:fields) {
                    int i = Integer.parseInt(field.split(":")[0]);
                    int j = Integer.parseInt(field.split(":")[1]);
                    Launcher.getInstance().getGame().getFeld()[i][j] = 1;
                    System.out.println("Adding field:" + i + " " + j);
                }


                Launcher.getInstance().getRunningMainUI().getRunningController().drawField(Launcher.getInstance().getGame().getFeld());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
