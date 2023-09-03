package battleShip;

import battleShip.entities.Player;
import battleShip.entities.Ship;

import java.util.ArrayList;

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
        Player player2 = new Player("Franco");
        ArrayList<Ship> ships = player1.getShips();

        System.out.println(player1.placeShip("A1", "A1", ships.get(0)));
        player1.showMapShip();
        System.out.println(player1.placeShip("D1", "E1", ships.get(2)));
        player1.showMapShip();

        player2.shoot("A1", player1);
        player2.showMap();
        player1.showMapShip();
    }
}

