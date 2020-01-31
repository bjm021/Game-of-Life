package de.bjm.gameoflife.logic;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 16.01.2020
 * @author
 */

import java.lang.StringBuilder;
public class GameOfLife {

    // Anfang Attribute
    int feldLaenge = 100;

    byte[][] Feld;

    int step = 0;

    // Ende Attribute


    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public byte[][] getFeld() {
        return Feld;
    }

    public GameOfLife() {
        Feld = new byte[feldLaenge][feldLaenge];

        Feld[12][7] = 1; Feld[13][7] = 1;
        Feld[12][8] = 1; Feld[13][8] = 1;



        Feld[12][16] = 1;   Feld[12][17] = 1;
        Feld[13][15] = 1;                       Feld[13][17] = 1;
        Feld[14][15] = 1;   Feld[14][16] = 1;



        Feld[14][23] = 1;   Feld[14][24] = 1;
        Feld[15][23] = 1;                       Feld[15][25] = 1;
        Feld[16][23] = 1;



        Feld[10][30] = 1;   Feld[10][31] = 1;
        Feld[11][29] = 1;                       Feld[11][31] = 1;
        Feld[12][29] = 1;   Feld[12][30] = 1;


        Feld[10][41] = 1;   Feld[10][42] = 1;
        Feld[11][41] = 1;   Feld[11][42] = 1;


        Feld[17][42] = 1;   Feld[17][43] = 1;
        Feld[18][42] = 1;                       Feld[18][44] = 1;
        Feld[19][42] = 1;



        Feld[22][31] = 1;   Feld[22][32] = 1;   Feld[22][33] = 1;
        Feld[23][31] = 1;
        Feld[24][32] = 1;
    }
    // Anfang Methoden

    public void clear() {
        Feld = new byte[feldLaenge][feldLaenge];
    }


    public void berechneNaechsteGeneration() {
        // TODO hier Quelltext einfügen
        byte[][] neuesFeld = new byte[feldLaenge][feldLaenge];

        for (int i = 1; i < feldLaenge-1; i++) {
            for (int j = 1 ; j < feldLaenge-1; j++) {
                byte zuPruefenZelle = Feld[i][j];

                //System.out.println(berechneLebendeNachbarn(i, j));


                if (zuPruefenZelle == 0) {
                    if (berechneLebendeNachbarn(i, j) == 3) {
                        neuesFeld[i][j] = 1;
                    }
                } else if (zuPruefenZelle == 1) {
                    if (berechneLebendeNachbarn(i, j) < 2) {
                        neuesFeld[i][j] = 0;
                    } else if (berechneLebendeNachbarn(i, j) > 3) {
                        neuesFeld[i][j] = 0;
                    } else {
                        neuesFeld[i][j] = 1;
                    } // end of if-else
                } // end of if
            } // end of for
        } // end of for

        Feld = neuesFeld;
        step++;
    }

    public void ausgeben() {
        // TODO hier Quelltext einfügen
        for (int i = 1; i < feldLaenge-1; i++) {
            for (int j = 1; j < feldLaenge-1; j++) {
                byte zelle = Feld[i][j];
                if (zelle == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" 0");
                } // end of if-else // end of if
            } // end of for
            System.out.println();
        } // end of for
        System.out.println();
    }

    public int berechneLebendeNachbarn(int x, int y) {
        // TODO hier Quelltext einfügen
        int lebendeNachbarn = 0;

        lebendeNachbarn = lebendeNachbarn + Feld[x-1][y-1];
        lebendeNachbarn = lebendeNachbarn + Feld[x-1][y];
        lebendeNachbarn = lebendeNachbarn + Feld[x-1][y+1];

        lebendeNachbarn = lebendeNachbarn + Feld[x][y-1];
        lebendeNachbarn = lebendeNachbarn + Feld[x][y+1];

        lebendeNachbarn = lebendeNachbarn + Feld[x+1][y-1];
        lebendeNachbarn = lebendeNachbarn + Feld[x+1][y];
        lebendeNachbarn = lebendeNachbarn + Feld[x+1][y+1];
        return lebendeNachbarn;

    }

    public void ausgebenLebendeNachbarn() {
        // TODO hier Quelltext einfügen
        for (int i = 1; i < feldLaenge-1; i++) {
            for (int j = 1; j < feldLaenge-1; j++) {
                System.out.print(" " + berechneLebendeNachbarn(i, j));
            } // end of for
            System.out.println();
        } // end of for
    }

    public int berechneLebende() {
        // TODO hier Quelltext einfügen
        int anzahl = 0;
        for (int i = 1; i < feldLaenge-1; i++) {
            for (int j = 1; j < feldLaenge-1; j++) {
                anzahl += Feld[i][j];
            } // end of for
        } // end of for
        return anzahl;
    }

    public String getFormattedAusgabe() {
        // TODO hier Quelltext einfügen

        StringBuilder sb = new StringBuilder();

        // TODO hier Quelltext einfügen
        for (int i = 1; i < feldLaenge-1; i++) {
            for (int j = 1; j < feldLaenge-1; j++) {
                if (Feld[i][j] == 0) {
                    sb.append("   .");
                } else {
                    sb.append("   0");
                } // end of if-else // end of if
            } // end of for
            sb.append("\n");
        } // end of for

        return sb.toString();
    }

    public void resetGame() {
        Feld = new byte[feldLaenge][feldLaenge];
        step = 0;
        clear();
    }

    // Ende Methoden

} // end of class GameOfLife


