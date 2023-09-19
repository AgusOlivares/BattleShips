package battleShip.entities;

/**
 * La clase abstracta MapElement representa a los elemento que se encuentra dentro del tablero. Almacena la
 * celda donde se encuentra y la funcion de como reaccionar al ser disparado.
 * @version 1.2, 8/9/2023
 * @author Martin Farres
 */
public abstract class MapElement {
    private Cell position;
    protected abstract void wasShot();
}
