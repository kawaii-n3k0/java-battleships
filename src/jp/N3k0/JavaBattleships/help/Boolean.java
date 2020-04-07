package jp.N3k0.JavaBattleships.help;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.help</b>
 */
public abstract class Boolean {
    @Deprecated
    public static boolean and(boolean a, boolean b) {
        return a && b;
    }
    public static boolean and(boolean...list) {
        int l = 0;
        for (boolean b : list) if (!b) l++;

        return l == 0;
    }

    @Deprecated
    public static boolean or(boolean a, boolean b) {
        return a || b;
    }
    public static boolean or(boolean...list) {
        int l = 0;
        for (boolean b : list) if (!b) l++;

        return l != 0;
    }

    @Deprecated
    public static boolean xor(boolean a, boolean b) {
        return a ^ b;
    }
    public static boolean xor(boolean...list) {
        int l = 0;
        for (boolean b : list) if (!b) l++;

        return and(nor(l == 0, l == list.length), l != list.length);
    }

    // ========================================================================================================

    @Deprecated
    public static boolean nand(boolean a, boolean b) {
        return !and(a, b);
    }
    public static boolean nand(boolean...list) {
        return !and(list);
    }

    @Deprecated
    public static boolean nor(boolean a, boolean b) {
        return !or(a, b);
    }
    public static boolean nor(boolean...list) {
        return !or(list);
    }

    @Deprecated
    public static boolean xnor(boolean a, boolean b) {
        return !xor(a, b);
    }
    public static boolean xnor(boolean...list) {
        return !xor(list);
    }
}
