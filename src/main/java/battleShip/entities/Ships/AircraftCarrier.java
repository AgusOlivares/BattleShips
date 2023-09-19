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
     * @param enemy El jugador contrario.
     * @return true si los disparos se realizaron exitosamente, false en caso contrario.
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player enemy) {
        // Select "v" to shoot one space up and one down from the original shootplace
        // Select "h" to shoot on the left and rightside from the original shootplace



        int originalRow = pos.charAt(0) - 65; //Valor ascii del caracter row
        int originalCol = Integer.parseInt(pos.substring(1)); //valor int de la col

        int newRow; //row of the new position to be shooted
        int newCol; //column of the new position to be shooted
        String newPosition;

        Scanner read = new Scanner(System.in);
        System.out.println(" Elige la orientacion del barrido: ");
        System.out.println("'h' : para barrido horizontal, 'v' : para barrido vertical");

        String orientationChosen = "";
        do {
            orientationChosen = read.nextLine().toLowerCase(); //Example: String orientationChosen = "v";
            if(!orientationChosen.equals("v") && !orientationChosen.equals("h")){
                System.out.println("Opción incorrecta, intente de nuevo");
            }
        }while (!orientationChosen.equals("v") && !orientationChosen.equals("h"));

        switch (orientationChosen){
            case "h":
                player.shoot(pos, enemy);

                newCol = originalCol - 1;
                if(newCol >= 1){
                    newPosition = pos.substring(0, 1) + Integer.toString(newCol);
                    player.shoot(newPosition, enemy);
                }

                newCol += 2;
                if(newCol <= 10){
                    newPosition = pos.substring(0, 1) + Integer.toString(newCol);
                    player.shoot(newPosition, enemy);
                }

                break;
            case "v":
                player.shoot(pos, enemy);
                String alphabet = "ABCDEFGHIJ";

                newCol = originalCol;
                newRow = originalRow - 1;
                if(newRow >= 0){
                    newPosition = alphabet.substring(newRow, newRow+1) + Integer.toString(newCol);
                    player.shoot(newPosition, enemy);
                }

                newRow = originalRow + 1;
                if(newRow <= 9){
                    newPosition = alphabet.substring(newRow, newRow+1) + Integer.toString(newCol);
                    player.shoot(newPosition, enemy);
                }

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
        System.out.println("La habilidad especial del Portaviones permite realizar disparos a los"
                + "\nlaterales o verticales de la posicion objetivo segun le sea indicado\n");
        System.out.println("h: para un disparo de barrido horizontal");
        System.out.println("v: para un barrido de barrido vertical\n");
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

        System.out.println("Vista del mapa enemigo al realizar un barrido horizontal en B2");
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

        System.out.println("Vista del mapa enemigo al realizar un barrido vertical en B2");
        System.out.println("");
    }

}