package jp.N3k0.JavaBattleships.tiles;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.tiles</b>
 */
public class WaterTile extends Tile {
    public WaterTile() {
        super("img/water_empty.png");
    }

    @Override
    void change(String newTexture) {
        if (super.hit) {
            super.texture = newTexture;
            super.hit = false;
        } else if (!super.hit) {
            super.texture = newTexture;
            super.hit = true;
        }
    }
}
