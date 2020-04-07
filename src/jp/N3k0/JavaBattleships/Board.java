package jp.N3k0.JavaBattleships;

import jp.N3k0.JavaBattleships.help.Console;
import jp.N3k0.JavaBattleships.ship.Ship;

import java.awt.*;
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
    private boolean bb = false, ca = false, dd = false, ss = false, cv = false;

    public Board() {
        init();
    }

    private void init() {
        for (int i = 0; i < board.length; i++) for (int j = 0; j < board[i].length; j++) board[i][j] = false;
        this.water_empty = getToolkit().getImage("img/tile_water_0.png");
    }

    public final void print(boolean prettify) {
        String map = prettify ? "" : "[";
        for (boolean[] row : board) {
            map += prettify ? String.format("%s\n", Arrays.toString(row)) : String.format("%s,", Arrays.toString(row));
        }

        System.out.printf(prettify ? "%s" : "%s]", map);
    }

    public final void place(Ship ship, int x, int y) {

        int type = this.checkShip(ship);

        switch (type) {
            case 0: bb = true; break;
            case 1: ca = true; break;
            case 2: dd = true; break;
            case 3: ss = true; break;
            case 4: cv = true; break;
            case -1: default: throw new IllegalArgumentException(String.format("Value is out of range: 0 | 4; value given is %s", type));
        }

        if (bb && ship.getShip() == Ship.Type.BATTLESHIP.getType()) return;
        else if(ca && ship.getShip() == Ship.Type.HEAVY_CRUISER.getType()) return;
        else if (dd && ship.getShip() == Ship.Type.DESTROYER.getType()) return;
        else if (ss && ship.getShip() == Ship.Type.SUBMARINE.getType()) return;
        else if (cv && ship.getShip() == Ship.Type.AIRCRAFT_CARRIER.getType()) return;

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
            if (ship.getOrientation() == 1) this.placeVertical(ship, x, y);
            else this.placeHorizontal(ship, x, y);
        }
        else Console.warn("Cannot place ship.");
    }

    // ========================================================================================================

    private int checkShip(Ship ship) {
        int type = ship.getShip();

        return type == 0xBB ? 0 : type == 0xCA ? 1 : type == 0xDD ? 2 : type == 0xEE ? 3 : type == 0xFF ? 4 : -1;
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
                    Image ship_tile = getToolkit().getImage("img/tile_ship_0.png");
                    g.drawImage(ship_tile, x * size, y * size, size, size, this);
                }
                else {
                    g.drawImage(this.water_empty, x * size, y * size, size, size, this);
                }
            }
        }

        // draw the null tile
        Image null_tile = getToolkit().getImage("img/tile_null.png");

        g.drawImage(null_tile, 0, 0, size, size, this);

        for (int i = 0; i < 11;i++) {
            Image tile_number = getToolkit().getImage("img/tile_" + this.coords(i) + ".png");
            g.drawImage(tile_number, i * size, 0, size, size, this);
        }
        for (int j = 0; j < 11;j++) {
            Image tile_letter = getToolkit().getImage("img/tile_" + (Integer.toString(j).equals("0") ? "" : Integer.toString(j)) + ".png");
            g.drawImage(tile_letter, 0, j * size, size, size, this);
        }
    }
}
