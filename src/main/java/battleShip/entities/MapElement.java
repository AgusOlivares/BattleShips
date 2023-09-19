package battleShip.entities;

/**
 * La clase abstracta MapElement representa a los elemento que se encuentra dentro del tablero. Almacena la
 * celda donde se encuentra y la funcion de como reaccionar al ser disparado.
 */
public abstract class MapElement {
    private Cell position;
    protected abstract void wasShot();
}
