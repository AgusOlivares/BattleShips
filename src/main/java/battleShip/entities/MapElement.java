package battleShip.entities;

abstract class MapElement {
    private Cell position;

    abstract void wasShot();
}
