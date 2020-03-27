package jp.N3k0.JavaBattleships.help;

import java.util.Date;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.help</b>
 */
public abstract class Console {
    public static void warn(String message) {

        final Date d = new Date();

        String temp = String.format("[%s:%s:%s]:", d.getHours(), d.getMinutes(), d.getSeconds());
        System.out.printf("%s%s", temp, message);
    }
}
