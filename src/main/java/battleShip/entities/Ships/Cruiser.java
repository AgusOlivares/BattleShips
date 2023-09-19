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

        boolean posibbleShoot = false;
        Game auxGame = new Game();
        shoot(player, pos, Enemy);
        Scanner read = new Scanner(System.in);
        while (!posibbleShoot) {
            auxGame.showShotsMap(player);
            System.out.println("Selecciona una posicion para el segundo disparo");
            String newPos = read.next();
            if (Enemy.getMap().getCell(newPos) != null){
                shoot(player, newPos, Enemy);
                posibbleShoot = true;
            } else {
                System.out.println("La posicion es invalida, intenta nuevamente");
            }
        }
        return true;
    }

    @Override
    public void showAbility() {
        System.out.println("Doble disparo: ");
        System.out.println("La habilidad especial del Crucero permite realizar dos disparos normales seguidos en las posiciones seleccionadas");
    }
    private Boolean shoot(Player player, String pos, Player Enemy) { // String like "A6" expected
        Cell cellP1 = player.getMap().getCell(pos);
        Cell cellP2 = Enemy.getMap().getCell(pos);
        try {
            cellP2.shot(); //shot p2
            player.getMap().addShotCell(cellP1);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void showExample(){

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
