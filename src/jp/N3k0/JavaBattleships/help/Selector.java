package jp.N3k0.JavaBattleships.help;

import javax.swing.*;
import java.awt.*;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.help</b>
 */
public final class Selector extends JFrame {
    public Selector() {
        super("Ships");
        setSize(200, 300);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().add(new Button("Add ship") {
            {

            }
        });
    }

    public void showUI() {
        setVisible(true);
    }
}
