package de.bjm.gameoflife.io;

import de.bjm.gameoflife.Launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileManager {

    public static boolean saveFile(File saveFile) {

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

    public boolean readFile(File file) {
        try {
            List<String> lines =  Files.readAllLines(file.toPath());

            String read = lines.get(0);
            String[] fields = read.split(",");

            for (String field:fields) {
                int i = Integer.parseInt(field.split(":")[0]);
                int j = Integer.parseInt(field.split(":")[1]);
                Launcher.getInstance().getGame().getFeld()[i][j] = 1;
            }

            return true;


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
