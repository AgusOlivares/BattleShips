package battleShip.entities.MapElements;

import battleShip.entities.Cell;
import battleShip.entities.MapElement;

import javax.swing.text.Position;

/**
 * La clase Decoy representa a un señuelo dentro del tablero. Almacena solamente a la celda que se encuentra asociada.
 * @version 1.2, 18/9/2023
 * @author Agustin Olivares
 */
public class Decoy extends MapElement {
    private final Cell position;

    //Contructor
    /**
     * Construye una nueva instancia de 'decoy' y lo asocia con el objeto Cell pasado como parametro.
     * @param position Cell
     */
    public Decoy(Cell position){
        this.position = position;
    }

    //Methods
    /**
     * Imprime por consola y deja saber al jugador que ha impactado a un barco.
     */
    @Override
    public void wasShot() {
        System.out.println("Un barco enemigo fue golpeado");
    }
}
