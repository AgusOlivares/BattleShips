package battleShip.entities.Ships;

import battleShip.entities.Map;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

public class Warship extends Ship{

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

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {

                if (enemyMap.getCell(i, j) == null) {
                    continue;
                } else if (enemyMap.getCell(i, j).getElement() != null) {
                    objectSpotted = true;
                }
            }
        }

        if (objectSpotted) {
            System.out.println("Un objeto ha aparecido en el radar!");
            return true;
        } else {
            System.out.println("No se ha encontrado nada");
            return false;
        }
    }

    @Override
    public Void showAbility() {

        System.out.println("Radar: ");
        System.out.println("La habilidad especial del Buque permite realizar el escaneo de una zona allrededor de la casilla seleccionada (3x3), ");
        System.out.print(" si solo hay agua en la zona escaneada no se detectara nada, ");
        System.out.print(" pero en caso de detectar un objeto distinto de agua se alertara al jugador con un mensaje, pero no sabra en que posicion se encuentra!");

        return null;
    }
}
