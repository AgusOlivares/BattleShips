package battleShip.entities.MapElements;

import battleShip.entities.Cell;
import battleShip.entities.MapElement;

/**
 * La clase Water representa que no hay ningun elemento, salvo agua. Almacena solamente a la celda que se encuentra asociada.
 * @version 1.2, 18/9/2023
 * @author Martin Farres
 */
public class Water extends MapElement {
    private Cell position;


    //Contructor
    /**
     * Construye una nueva instancia de agua y lo asocia con el objeto Cell pasado como parametro.
     * @param position Cell
     */
    public Water(Cell position){
        this.position = position;
    }


    //Getters
    /**
     * Retorna la celda donde se encuentra
     * @return Cell
     */
    public Cell getPosition() {
        return position;
    }


    //Methods
    /**
     * Imprime por consola y deja saber al jugador que no se ha impactado nada
     */
    @Override
    public void wasShot() {
        System.out.println("¡Agua!");
    }
}
