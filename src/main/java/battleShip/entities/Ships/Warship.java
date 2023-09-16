package battleShip.entities.Ships;

import battleShip.entities.Map;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

public class Warship extends Ship {

    public Warship() {
        super(3);
    }


    /**
     * Implementacion polimorfica del metodo de 'Ship', utiliza la habilidad especial del Buque.
     * Escanea una zona cuadrada alrededor de la posicion objetivo, indica si hay algun elemento dentro de la zona no especifica qué.
     *
     * @param player El jugador que invoca el metodo.
     * @param pos    La posicion objetivo.
     * @param Enemy  El jugador contrario.
     * @return
     * @see Ship
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {
        // Modify conditions to avoid erros if an adjacent position is out of bounds
        int row = pos.charAt(0) - 'A';
        int col = Integer.parseInt(pos.substring(1));

        boolean objectSpotted = false;
        Map enemyMap = Enemy.getMap();

        for (int i = row - 1; i < row + 1; i++) {
            for (int j = col - 1; j < col + 1; j++) {
                if (enemyMap.getCell(i, j).getElement() != null) {
                    objectSpotted = true;
                } else {
                    continue;
                }
            }
        }

        if (objectSpotted) {
            System.out.println("Object spotted in radar");
            return true;
        } else {
            System.out.println("Nothing in radar");
            return false;
        }


    }
}
