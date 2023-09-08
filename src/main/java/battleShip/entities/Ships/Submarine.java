package battleShip.entities.Ships;

import battleShip.entities.Map;
import battleShip.entities.Player;
import battleShip.entities.Ship;
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
     * Escanea una zona cuadrada allrededor de la posicion objetivo, indica si hay algun elemento dentro de la zona no especifica qué.
     * @see Ship
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario.
     * @return
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {
        // Modify conditions to avoid erros if an adjacent position is out of bounds
        int row = pos.charAt(0) - 'A';

        int col = Integer.parseInt(pos.substring(1));

        boolean objectSpotted = false;
        Map enemyMap = Enemy.getMap();

        for (int i = row-1; i < row+1; i++){
            for (int j = col-1; j < col+1; j++) {
                if(enemyMap.getCell(i,j).getElement() != null){
                    objectSpotted = true;
                }
            }
        }

        if (objectSpotted){
            System.out.println("Object spotted in radar");
            return true;
        } else {
            System.out.println("Nothing in radar");
            return false;
        }


    }


}
