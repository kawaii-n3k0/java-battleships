package jp.N3k0.JavaBattleships.help;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.help</b>
 */
public abstract class Arrays {
    public static <E> boolean contains(E[] array, E e) {
        for (E element : array) if (element == e) return true;
        return false;
    }

    public static boolean is(boolean[] array, boolean value) {
        if (value) return Boolean.and(array);
        return Boolean.nor(array);
    }
}
