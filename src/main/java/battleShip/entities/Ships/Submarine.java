package battleShip.entities.Ships;

import battleShip.entities.*;
import org.jetbrains.annotations.NotNull;
/**
 * Una clase encargada de crear un Submarino ('Submarine') Subclase de 'Ship'
 * @see Ship
 * @author Agustin Olivares
 * @version 1.2, 08/09/2023
 */
public class Submarine extends Ship  {

    public Submarine() {
        super(2);
    }

    /**
     * Implementacion polimorfica del metodo de 'Ship', utiliza la habilidad especial del Submarino.
     * Coloca un señuelo en la posicion indicada, cuando el jugador contrario dispare en esta area figurara como que se ha disparado en un barco sin afectar la lista de barcos del otro jugador.
     * @see battleShip.entities.Decoy
     * @see Ship
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario (En este caso no es necesario que sea completado).
     * @return
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy) {

        Cell CellP1 = player.getMap().getCell(pos);

        if(CellP1.getElement() != null || CellP1.getWasShot()){
            System.out.println("Invalid position"); // controlar no quitarle las cargas al jugador
            return false;
        } else {
            Decoy Senuelo = new Decoy(CellP1);
            CellP1.setElement(Senuelo);
            System.out.println("Decoy placed successfully");
        }
        return true;
    }
}

