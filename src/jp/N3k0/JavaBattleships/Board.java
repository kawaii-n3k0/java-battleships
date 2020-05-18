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

    private int[][] board = new int[10][10];
    private boolean bb = false, ca = false, dd = false, ss = false, cv = false;

    public Board() {
        init();
    }

    private void init() {
        for (int i = 0; i < board.length; i++) for (int j = 0; j < board[i].length; j++) board[i][j] = 0;
        this.water_empty = getToolkit().getImage("img/tile_water_0.png");
    }

    public final void print(boolean prettify) {
        String map = prettify ? "" : "[";
        for (int[] row : board) {
            map += prettify ? String.format("%s\n", Arrays.toString(row)) : String.format("%s,", Arrays.toString(row));
        }

        System.out.printf(prettify ? "%s" : "%s]", map);
    }

    public final void place(Ship ship, int x, int y) {

        Console.log("Placing ship in position: " + String.format("%s, %s", x, y));

        int type = this.checkShip(ship);

        Console.log("Type: " + type);

        switch (type) {
            case 0: bb = true; break;
            case 1: ca = true; break;
            case 2: dd = true; break;
            case 3: ss = true; break;
            case 4: cv = true; break;
            case -1: default: throw new IllegalArgumentException(String.format("Value is out of range: 0 | 4; value given is %s", type));
        }

        if (!bb && ship.getShip() == Ship.Type.BATTLESHIP.getType()) return;
        else if(!ca && ship.getShip() == Ship.Type.HEAVY_CRUISER.getType()) return;
        else if (!dd && ship.getShip() == Ship.Type.DESTROYER.getType()) return;
        else if (!ss && ship.getShip() == Ship.Type.SUBMARINE.getType()) return;
        else if (!cv && ship.getShip() == Ship.Type.AIRCRAFT_CARRIER.getType()) return;

        boolean clear = true;
        int cleared = 0;
        if (ship.getOrientation() == 0) {
            Console.log("Placing ship horizontally");
            if (ship.getLength() + y <= 10) {
                for (int j = 0;j < ship.getLength();j++) {
                    if (board[x][j + y] == 1) { cleared++; clear = cleared == 0; }
                }
            }
            else clear = false;
        }
        else {
            Console.log("Placing ship vertically");
            if (ship.getLength() + x <= 10) {
                for (int i = 0;i < ship.getLength();i++) {
                    if (board[i + x][y] == 1) { cleared++; clear = cleared == 0; }
                }
            }
            else clear = false;
        }

        Console.log("Cleared: " + clear);

        if (clear) {
            Console.log("Placing ship");
            if (ship.getOrientation() == 1) this.placeVertical(ship, x, y);
            else this.placeHorizontal(ship, x, y);
        }
        else Console.warn("Cannot place ship.");
    }

    // ========================================================================================================

    private int checkShip(Ship ship) {
        int type = ship.getShip();

        Console.log("Checking for the ship of type: " + type);

        return type == 0xBB ? 0 : type == 0xCA ? 1 : type == 0xDD ? 2 : type == 0xEE ? 3 : type == 0xFF ? 4 : -1;
    }

    // ========================================================================================================

    private void placeHorizontal(Ship ship, int x, int y) {
        if (ship.getLength() + y <= 10) {
            for(int j = 0; j < ship.getLength(); j++) board[x][j + y] = ship.isSunk() ? 3 : ship.getHit()[j] ? 2 : 1;
        }
    }

    private void placeVertical(Ship ship, int x, int y) {
        if (ship.getLength() + x <= 10) {
            for (int i = 0; i < ship.getLength(); i++) board[i + x][y] = ship.isSunk() ? 3 : ship.getHit()[i] ? 2 : 1;
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
        Console.log("Drawing...");
        int size = 30;
        for (int y = 1; y < board.length + 1; y++) {
            for (int x = 1; x < board[y - 1].length + 1; x++) {
                if (board[y - 1][x - 1] == 1) {
                    Image ship_tile = getToolkit().getImage("img/tile_ship_0.png");
                    g.drawImage(ship_tile, x * size, y * size, size, size, this);
                }
                else if (board[y - 1][x - 1] == 2) {
                    Image ship_tile = getToolkit().getImage("img/tile_ship_1.png");
                    g.drawImage(ship_tile, x * size, y * size, size, size, this);
                }
                else if (board[y - 1][x - 1] == 3) {
                    Image ship_tile = getToolkit().getImage("img/tile_ship_2.png");
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
