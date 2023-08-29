package battleShip.entities;

import battleShip.entities.Ships.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private String name;
    private Ship[] ships;
    private Map map;
    private int charges;

    //Constructor
    public Player(String name){
        this.name = name;

        ArrayList<Ship> theShipList = new ArrayList<Ship>();
        theShipList.add(new Boat());
        theShipList.add(new Cruiser("test"));
        theShipList.add(new Submarine("test"));
        theShipList.add(new Warship("test"));
        theShipList.add(new AircraftCarrier("test"));
        this.ships = theShipList.toArray(new Ship[5]);

        this.map = new Map();
        this.charges = 0;
    }

    //Getters
    public String getName() {
        return name;
    }

    public Ship[] getShips() {
        return ships;
    }

    public Map getMap() {
        return map;
    }
    public int getCharges() {
        return charges;
    }

    //Setters
    public void setShips(Ship[] ships) {
        this.ships = ships;
    }
    public void setCharges(int charges) {
        this.charges = charges;
    }

    //Methods
    public boolean placeShip(){
        return true;
    }
    public String shoot(String pos, Player p2){ // String like "A6" expected
        int x = pos.substring(0,1).charAt(0) - 'A';
        int y = Integer.parseInt(pos.substring(1)) - 1;
        Cell cellP1 = this.map.getBoard()[x][y];
        Cell cellP2 = p2.getMap().getBoard()[x][y];
        if (!cellP2.getWasShot()){
            cellP2.setWasShot(true);
            if (cellP2.getShip() != null){
                cellP1.setElement("X");
                return "Hit";
            }
            else{
                cellP1.setElement("O");
                return "Water";
            }

        }
        return "Position already shot, pick another position: ";
    }

    public String shoot(String pos, Ship ship, Player p2){
        return "x";
    }

    public void showMap(){
        // Top Number Legend
        int charLegendCnt = 64;
        for (int i = 0; i < this.map.getBoard().length + 1; i++){
            if (i == 0){
                System.out.printf("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < this.map.getBoard().length; i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < this.map.getBoard()[0].length; j++){
                // Indicators
                System.out.printf("| %s ", map.getBoard()[i][j].getElement());
            }
            System.out.println(" |");
        }
        System.out.println();
    }

    public void showMapshipPlacement(){
        // Top Number Legend
        int charLegendCnt = 64;
        for (int i = 0; i < this.map.getBoard().length + 1; i++){
            if (i == 0){
                System.out.printf("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < this.map.getBoard().length; i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < this.map.getBoard()[0].length; j++){
                // Indicators
                if (map.getBoard()[i][j].getShip() != null ){
                    System.out.printf("| � ");
                }
                else {
                    System.out.printf("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }
}
