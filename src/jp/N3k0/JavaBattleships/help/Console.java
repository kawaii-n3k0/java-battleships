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
        String temp = format("WARN");
        System.out.printf("%s%s%n", temp, message);
    }

    public static void log(String message) {
        String temp = format("LOG");
        System.out.printf("%s%s%n", temp, message);
    }

    private static String format(String prefix) {
        final Date d = new Date();

        return String.format("[%s@%s:%s:%s]:", prefix, d.getHours(), d.getMinutes(), d.getSeconds());
    }
}
