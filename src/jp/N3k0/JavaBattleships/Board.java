package jp.N3k0.JavaBattleships;

import jp.N3k0.JavaBattleships.help.Console;
import jp.N3k0.JavaBattleships.ship.Ship;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships</b>
 */
public final class Board extends Canvas {

    private Image water_empty;

    private boolean[][] board = new boolean[10][10];
    private boolean bb = false, ca = false, dd = false, ss, cv;

    public Board() {
        init();
    }

    private void init() {
        for (int i = 0; i < board.length; i++) for (int j = 0; j < board[i].length; j++) board[i][j] = false;
        this.water_empty = getToolkit().getImage("img/water_empty.png");
    }

    public final void print(boolean prettify) {
        String map = prettify ? "" : "[";
        for (boolean[] row : board) {
            map += prettify ? String.format("%s\n", Arrays.toString(row)) : String.format("%s,", Arrays.toString(row));
        }

        System.out.printf(prettify ? "%s" : "%s]", map);
    }

    public final void place(Ship ship, int x, int y) {
        boolean clear = true;
        int cleared = 0;
        if (ship.getOrientation() == 0) {
            if (ship.getLength() + y < 10) {
                for (int j = 0;j < ship.getLength();j++) {
                    if (board[x][j + y]) { cleared++; clear = cleared == 0; }
                }
            }
            else clear = false;
        }
        else {
            if (ship.getLength() + x < 10) {
                for (int i = 0;i < ship.getLength();i++) {
                    if (board[i + x][y]) { cleared++; clear = cleared == 0; }
                }
            }
            else clear = false;
        }

        if (clear) {
            if (ship.getOrientation() == 1) placeVertical(ship, x, y);
            else placeHorizontal(ship, x, y);
        }
        else Console.warn("Cannot place ship.");
    }

    // ========================================================================================================

    private void placeHorizontal(Ship ship, int x, int y) {
        if (ship.getLength() + y < 10) {
            for(int j = 0; j < ship.getLength(); j++) board[x][j + y] = true;
        }
    }

    private void placeVertical(Ship ship, int x, int y) {
        if (ship.getLength() + x < 10) {
            for (int i = 0; i < ship.getLength(); i++) board[i + x][y] = true;
        }
    }

    // ========================================================================================================

    private String coords(int i) {
        switch (i) {
            case 0: return "";
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "E";
            case 6: return "F";
            case 7: return "G";
            case 8: return "H";
            case 9: return "I";
            case 10: return "J";
            case 11: return "";
            default: throw new IllegalArgumentException();
        }
    }

    // ========================================================================================================


    @Override
    public void paint(Graphics g) {
        int size = 30;
        for (int x = 1; x < board.length + 1; x++) {
            for (int y = 1; y < board[x - 1].length + 1; y++) {
                if (board[x - 1][y - 1]) {
                    g.setColor(Color.red);
                    g.fillRect(y * size, x * size, size, size);
                    g.setColor(Color.black);
                    g.drawRect(y * size, x * size, size, size);
                }
                else {
                    g.drawImage(this.water_empty, x * size, y * size, size, size, this);
                }
            }
        }
        for (int i = 0; i < 11;i++) {
            g.setColor(Color.lightGray);
            g.fillRect(i * size, 0, size, size);
            g.setColor(Color.black);
            g.drawRect(i * size, 0, size, size);
            g.drawString(coords(i), i * 30 + 10, 20);
        }
        for (int j = 0; j < 11;j++) {
            g.setColor(Color.lightGray);
            g.fillRect(0, j * size, size, size);
            g.setColor(Color.black);
            g.drawRect(0, j * size, size, size);
            g.drawString(Integer.toString(j).equals("0") ? "" : Integer.toString(j), 10, j * 30 + 20);
        }
    }
}
