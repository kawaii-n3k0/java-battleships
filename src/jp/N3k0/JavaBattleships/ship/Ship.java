package jp.N3k0.JavaBattleships.ship;

/**
 * @project Java_Battleships
 * @copyright N3k0 (c) 2020
 *
 * <b>File inside package: jp.N3k0.JavaBattleships.ship</b>
 */
public abstract class Ship {

    private int length, orientation, ship;

    Ship(Type type, Orientation orientation) {

        this.orientation = orientation.orientation ? 1 : 0;

        this.ship = type.type;

        switch (type) {
            case DESTROYER: this.length = 2; break;
            case BATTLESHIP: this.length = 4; break;
            case SUBMARINE: case HEAVY_CRUISER: this.length = 3; break;
            case AIRCRAFT_CARRIER: this.length = 5; break;
            default: throw new IllegalArgumentException("Illegal value given as jp.Nk30.JavaBattleships.ship.Ship.Type");
        }
    }

    public int getLength() {
        return length;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getShip() {
        return ship;
    }

    // ========================================================================================================

    public enum Type {
        BATTLESHIP(0xBB),
        HEAVY_CRUISER(0xCA),
        DESTROYER(0xDD),
        SUBMARINE(0xEE),
        AIRCRAFT_CARRIER(0xFF);

        private final int type;
        Type(int type) { this.type = type; }

        public int getType() {
            return type;
        }
    }

    // ========================================================================================================

    public enum Orientation {
        HORIZONTAL(false),
        VERTICAL(true);

        private final boolean orientation;
        Orientation(boolean orientation) { this.orientation = orientation; }

        public boolean isOrientation() {
            return orientation;
        }
    }
}
