package battleShip.entities;

public abstract class MapElement {
    private Cell position;

    protected abstract void wasShot();
}
