package battleShip.entities.MapElements;

import battleShip.entities.Cell;
import battleShip.entities.MapElement;

/**
 * La clase Island representa a una isla dentro del tablero. Almacena solamente a la celda que se encuentra asociada.
 * @version 1.2, 18/9/2023
 * @author Martin Farres
 */
public class Island extends MapElement {
    private Cell position;


    //Contructor
    /**
     * Construye una nueva instancia de isla y lo asocia con el objeto Cell pasado como parametro.
     * @param position Cell
     */
    public Island(Cell position){
        this.position = position;
        position.setElement(this);
    }


    //Getters
    /**
     * Retorna la celda donde se encuentra la isla
     * @return Cell
     */
    public Cell getPosition() {
        return position;
    }


    //Methods
    /**
     * Imprime por consola y deja saber al jugador que se ha impactado una isla
     */
    @Override
    public void wasShot() {
        System.out.println("Contacto con una isla enemegia. ¿Isla enemiga?");
    }
}
