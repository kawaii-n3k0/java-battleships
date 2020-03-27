package jp.N3k0.JavaBattleships.tiles;

import jp.N3k0.JavaBattleships.ship.Ship;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.tiles</b>
 */
public abstract class Tile {
    boolean hit = false;
    String texture;

    Tile(String texture) {
        this.texture = texture;
    }

    native void setTexture(Ship.Orientation orientation);
    native void place();

    abstract void change(String newTexture);

    Tile getTile() {
        return this;
    }
}
