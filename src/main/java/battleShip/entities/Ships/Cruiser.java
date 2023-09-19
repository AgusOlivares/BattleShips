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
 *
 */
public class Cruiser extends Ship implements SpecialShipInterface {

    public Cruiser() {
        super(4);
    }

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

    @Override
    public void showAbility() {
        System.out.println("Doble disparo: ");
        System.out.println("La habilidad especial del Crucero permite realizar dos disparos "
                + "\nnormales seguidos en las posiciones seleccionadas\n");
    }

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
