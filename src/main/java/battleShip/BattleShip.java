package battleShip;

import battleShip.entities.Cell;
import battleShip.entities.Game;
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



        /*Game game = new Game(25);
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        ArrayList<Ship> ships = player1.getShips();

        player1.placeShip("A1", "A1", ships.get(0));
        game.showShipMap(player1);
        player1.placeShip("A3", "A4", ships.get(2));
        player1.placeShip("B2", "B5", ships.get(1));
        System.out.println();
        Cell cell = player1.getMap().getCell("A1");



        game.showShipMap(player1);
        player2.shoot("A3", player1);
        player2.shoot("A9", player1);
        player2.shoot("E3", player1);
        player2.shoot("R3", player1);
        player2.shoot("A13", player1);
        game.showShotsMap(player2);
        */
        Game game = new Game();
        game.playGame();

    }
}

