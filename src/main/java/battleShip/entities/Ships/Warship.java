package battleShip.entities.Ships;

import battleShip.Interface.SpecialShipInterface;
import battleShip.entities.Map;
import battleShip.entities.MapElements.*;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;
/**
 * Una clase encargada de crear un Crusero ('Cruiser') Subclase de 'Ship' que implementa la interfaz 'SpecialShipInterface
 * @see Ship
 * @see SpecialShipInterface
 * @author Agustin Olivares
 * @version 1.4, 08/09/2023
 */
public class Warship extends Ship implements SpecialShipInterface {

    /**
     * Construye un objeto Warship, inicializando el atributo 'length' en 3.
     */
    public Warship() {
        super(3);
    }


    /**
     * Implementacion polimorfica del metodo de 'Ship', utiliza la habilidad especial del Buque.
     * Escanea una zona cuadrada alrededor de la posicion objetivo, indica si hay algun elemento dentro de la zona no especifica qué.
     * @param player El jugador que invoca el metodo.
     * @param pos    La posicion objetivo.
     * @param Enemy  El jugador contrario.
     * @return retorna true si se ha podido escanear la zona
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
                } else if (!(enemyMap.getCell(i, j).getElement() instanceof Water)) {
                    objectSpotted = true;
                }
            }
        }

        if (objectSpotted) {
            System.out.println("Un objeto ha aparecido en el radar!");
        } else {
            System.out.println("No se ha detectado nada");
        }
        return true;
    }

    /**
     * Metodo encargado de explicar al jugador la habilidad del barco
     */
    @Override
    public void showAbility() {

        System.out.println("Radar: ");
        System.out.println("La habilidad especial del Buque permite realizar el escaneo "
                + "\nde una zona allrededor de la casilla seleccionada (3x3), si" + 
                " \nsólo hay agua en la zona escaneada no se detectará nada, en" + 
                "\ncaso de detectar un objeto distinto de agua se alertará al"
                + "\njugador con un mensaje, pero no sabra en que posicion se encuentra! \n");
    }

    /**
     * Imprime por consola una matriz que muestra un ejemplo de la habilidad del barco.
     */
    @Override
    public void showExample(){

        int charLegendCnt = 64;

        // Encabezado de números
        System.out.print("   ");
        for (int i = 1; i <= 3; i++) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");

        // Llenado y leyenda de filas
        for (int i = 0; i < 3; i++) {

            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);

            // Condicionales para los caracteres
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.print("| □ ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Mapa enemigo con un barco en B2");
        System.out.println("");

        charLegendCnt = 64;

        // Encabezado de números
        System.out.print("   ");
        for (int i = 1; i <= 3; i++) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");

        // Llenado matriz y leyenda de filas
        for (int i = 0; i < 3; i++) {

            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);

            // Condicionales para los caracteres
            for (int j = 0; j < 3; j++) {
                System.out.print("|   ");
            }
            System.out.println("|");
        }
        System.out.println("Un objeto ha aparecido en el radar!");
        System.out.println("Mapa propio al lanzar el radar en la posicion C1");
        System.out.println("");

    }
}
