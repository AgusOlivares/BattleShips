package battleShip.entities.Ships;

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
public class AircraftCarrier extends Ship {
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

                shoot(player, newPosition, Enemy);

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
    public Void showAbility() {

        System.out.println("Disparo de Barrido: ");
        System.out.println("La habilidad especial del Portaviones permite realizar disparos a los laterales o verticales de la posicion objetivo segun le sea indicado");
        System.out.println("h: para un disparo de barrido horizontal");
        System.out.println("v: para un barrido de barrido vertical");

        return null;
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