package battleShip.entities.Ships;

import battleShip.Interface.SpecialShipInterface;
import battleShip.entities.Cell;
import battleShip.entities.Game;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

/**
 * Una clase encargada de crear un Crusero ('Cruiser') Subclase de 'Ship' que implementa la interfaz 'SpecialShipInterface
 * @see Ship
 * @see SpecialShipInterface
 * @author Agustin Olivares
 * @version 1.4, 08/09/2023
 */
public class Cruiser extends Ship implements SpecialShipInterface {

    /**
     * Construye un objeto Cruiser, inicializando el atributo 'length' en 4.
     */
    public Cruiser() {
        super(4);
    }


    /**
     * Implementacion polimorfica del metodo de 'SpecialShipInterface', utiliza la habilidad especial del Crusero, "Doble Disparo".
     * Realiza 2 disparos seguidos.
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario.
     * @return true si los disparos se realizaron exitosamente, false en caso contrario.
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {

        Game auxGame = new Game();
        boolean wasShotSuccessful = false;
        do {
            wasShotSuccessful = player.shoot(pos, Enemy);
            if (!wasShotSuccessful) {
                System.out.println("Intente hacer el primer disparo nuevamente");
                pos = auxGame.askCoordinates();
            }
        } while (!wasShotSuccessful);
        wasShotSuccessful = false;

        System.out.println("\nA continuación realizarás el segundo disparo");
        do {
            String position = auxGame.askCoordinates();
            wasShotSuccessful = player.shoot(position, Enemy);
            if (!wasShotSuccessful) {
                System.out.println("Incorrecto, intente de nuevo");
            }
        } while (!wasShotSuccessful);

        return true;
    }

    /**
     * Metodo encargado de explicar al jugador la habilidad del barco
     */
    @Override
    public void showAbility() {
        System.out.println("Doble disparo: ");
        System.out.println("La habilidad especial del Crucero permite realizar dos disparos "
                + "\nnormales seguidos en las posiciones seleccionadas\n");
    }

    /**
     * Imprime por consola una matriz que muestra un ejemplo de la habilidad del barco.
     */
    @Override
    public void showExample() {

        int charLegendCnt = 64;

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
                if (i == 1 && j == 1) {
                    System.out.print("| X ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Mapa propio al realizar un primer disparo en B2");
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
                if ((i == 1 && j == 1) || (i == 2 && j == 0)) {
                    System.out.print("| X ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("Mapa propio al realizar un segundo disparo en C1");
        System.out.println("");

    }
}
