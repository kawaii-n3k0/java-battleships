package jp.N3k0.JavaBattleships;

import jp.N3k0.JavaBattleships.help.Selector;

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

        Board board = new Board();

        frame.getContentPane().add(board);

        Selector selector = new Selector();

        selector.showUI();
    }
}
