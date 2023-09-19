package battleShip.entities.Ships;

import battleShip.Interface.SpecialShipInterface;
import battleShip.entities.Cell;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * Una clase encargada de crear un Portaaviones ('AircraftCarrier') Subclase de 'Ship' que implementa la interfaz 'SpecialShipInterface
 * @see Ship
 * @see SpecialShipInterface
 * @author Agustin Olivares
 * @version 1.4, 08/09/2023
 */
public class AircraftCarrier extends Ship implements SpecialShipInterface {
    public AircraftCarrier() {
        super(5);
    }

    /**
     * Implementacion polimorfica del metodo de 'SpecialShipInterface', utiliza la habilidad especial del Portaviones, "Barrido".
     * Realiza disparos a los laterales o verticales de la posicion objetivo segun le sea indicado
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario.
     * @return true si los disparos se realizaron exitosamente, false en caso contrario.
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {
        // Select "v" to shoot one space up and one down from the original shootplace
        // Select "h" to shoot on the left and rightside from the original shootplace



        int originalRow = pos.charAt(0); //Valor ascii del caracter row
        int originalCol = Integer.parseInt(pos.substring(1)); //valor int de la col

        char newRow; //row of the new position to be shooted
        int newCol; //column of the new position to be shooted
        String newPosition;

        Scanner read = new Scanner(System.in);
        System.out.println(" Elige la orientacion del barrido: ");
        System.out.println("'h' : para barrido horizontal, 'v' : para barrido vertical");

        String orientationChosen = read.next(); //Example: String orientationChosen = "v";

        switch (orientationChosen){
            case "h":
                shoot(player, pos, Enemy);

                newRow = pos.charAt(0);
                newCol = originalCol - 1;
                newPosition = "%s%d".formatted(newRow, newCol);


                newCol += 2;
                newPosition = "%s%d".formatted(newRow, newCol);
                shoot(player, newPosition, Enemy);
                break;
            case "v":
                shoot(player, pos, Enemy);

                newCol = originalCol;
                newRow = (char) (originalRow - 1);
                newPosition = "%c%d".formatted(newRow, newCol);

                shoot(player, newPosition, Enemy);

                newRow = (char) (originalRow + 1);
                newPosition = "%c%d".formatted(newRow, newCol);
                shoot(player, newPosition, Enemy);
                break;
        }

        return true;

    }

    /**
     * Metodo encargado de explicar al jugador la habilidad del barco
     *
     */
    @Override
    public void showAbility() {

        System.out.println("Disparo de Barrido: ");
        System.out.println("La habilidad especial del Portaviones permite realizar disparos a los laterales o verticales de la posicion objetivo segun le sea indicado");
        System.out.println("h: para un disparo de barrido horizontal");
        System.out.println("v: para un barrido de barrido vertical");
    }
    @Override
    public void showExample(){

        int charLegendCnt = 64;

        // Encabezado de la columna de números
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
                if (i == 1) {
                    System.out.print("| X ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Mapa propio al realizar un barrido horizontal en B2");
        System.out.println("");

        charLegendCnt = 64;

        //Encabezado
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
                if (j == 1) {
                    System.out.print("| X ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Mapa propio al realizar un barrido vertical en B2");
        System.out.println("");
    }

    /**
     * Metodo encargado de realizar los disparos correspondientes a la habilidad, separada de la de player para mayor independencia de la clase.
     * @param player Jugador que activa la habilidad
     * @param pos posicion para el disparo
     * @param Enemy Posicion bjetivo del disparo
     * @return true si los disparos fueron realizados exitosamente, false en caso contrario.
     */

    private Boolean shoot(Player player, String pos, Player Enemy) { // String like "A6" expected
        Cell cellP1 = player.getMap().getCell(pos);
        Cell cellP2 = Enemy.getMap().getCell(pos);

        try {
            cellP2.shot(); //shot p2
            player.getMap().addShotCell(cellP1);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}