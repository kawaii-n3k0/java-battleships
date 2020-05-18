package jp.N3k0.JavaBattleships;

import jp.N3k0.JavaBattleships.help.Console;
import jp.N3k0.JavaBattleships.ship.*;

import javax.swing.*;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships</b>
 */
public class Main {

    private static final JFrame frame = new JFrame("Battleships") {
        {
            setSize(335, 359);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
        }
    };

    public static void main(String[] args) {

        AircraftCarrier cv = new AircraftCarrier(Ship.Orientation.VERTICAL);
        Battleship bb = new Battleship(Ship.Orientation.HORIZONTAL);
        HeavyCruiser ca = new HeavyCruiser(Ship.Orientation.VERTICAL);
        Destroyer dd = new Destroyer(Ship.Orientation.HORIZONTAL);
        Submarine ss = new Submarine(Ship.Orientation.HORIZONTAL);

        Board board = new Board();

        frame.getContentPane().add(board);

        board.print(true);

        cv.hit(1);
        dd.hit(0);
        dd.hit(1);
        dd.sink();

        Console.log("Destroyer Sunk status: " + dd.isSunk());

        board.place(cv , 0, 0);
        board.place(bb, 6, 2);
        board.place(dd, 9, 8);
        board.place(ca, 0, 5);
        board.place(ss, 3, 6);

        board.print(true);
    }
}
