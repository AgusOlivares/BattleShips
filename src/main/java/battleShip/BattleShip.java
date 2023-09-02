package battleShip;

import battleShip.entities.Player;
import battleShip.entities.Ship;

import java.util.Arrays;

public class BattleShip {

    public static void main(String[] args) {
//        System.out.println("Mi flota");
//        System.out.println("|�|  Parte de barco intacta");
//        System.out.println("|X|  Parte de barco impactada");
//        int decimalValue = Integer.parseInt("10100100", 2);
//        char character = (char) decimalValue;
//        System.out.println("Flota enemiga");
//        System.out.println("|"+ character+"|  Acierto");
//        System.out.println("|o|  Agua/le chingó");
//        System.out.println("Ambas flotas");
//        System.out.println("| |  Agua - Sin conocer")


        Player player1 = new Player("Tati");
        Ship[] ships = player1.getShips();

        player1.showMapshipPlacement();
        player1.placeShip("A1", "A1", ships[0] );
        player1.showMapshipPlacement();
        player1.placeShip("A9", "J9", ships[2] );
        player1.showMapshipPlacement();
    }
}

