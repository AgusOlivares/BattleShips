package battleShip.entities;

import battleShip.entities.Ships.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Player {
    private final String name;
    private Ship[] ships;
    private final Map map;
    private int charges;

    //Constructor
    public Player(String name){
        this.name = name;

        ArrayList<Ship> theShipList = new ArrayList<>(); // <? extends Ship>
        theShipList.add(new Boat());
        theShipList.add(new Cruiser());
        theShipList.add(new Submarine());
        theShipList.add(new Warship());
        theShipList.add(new AircraftCarrier());
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
    public boolean placeShip(@NotNull String startPos,@NotNull String finalPos, @NotNull Ship ship){
        int row = startPos.substring(0,1).charAt(0) - 'A';
        int col = Integer.parseInt(startPos.substring(1)) - 1;
        int rowFinal = finalPos.substring(0,1).charAt(0) - 'A';
        int colFinal = Integer.parseInt(finalPos.substring(1)) - 1;
        ArrayList<Cell> validCells = new ArrayList<>();

        try{
            Cell startCell = this.map.getCell(startPos);
            // Initial Position is valid?
            HelperPlaceShip(row, col);

            /*if ship.length == 1{

            }*/

            // Check it is not diagonal
            if (abs(row-rowFinal) > 0 && abs(col-colFinal) > 0){
                throw new Exception("The Position is not valid");
            }

            // Checks length of boat is the same as length of placement
            if ((abs(row-rowFinal) != ship.length) || (abs(col-colFinal) != ship.length)){
                throw  new Exception("The position selected should support the ships length: " + ship.length);
            }

            // Are all the position selected available?
            if (abs(row-rowFinal) > 0){ // Movement in row or col?
                for (int j = row; j <= rowFinal; j++) { // Checks all positions are available, if not raises exception
                    HelperPlaceShip(j, col, validCells);
                }
            }else {
                for (int j = col; j <= colFinal; j++){
                    HelperPlaceShip(row, j, validCells);
                }
            }

            //Place ship inside cell
            for(Cell cell : validCells){
                cell.setShip(ship);
            }

        }catch (Exception e){
            // Catches out of bounds error
            System.out.printf("%s", e);
            return false;
        }

        return true;
    }

    private void HelperPlaceShip(int row,int col) throws Exception{ //Se puede mejorar pq checkea mas de una vez a ciertas celdas
        for(int i = -1; i < 2; i++ ){ //Checks one position to its right,left,up,down
            Cell cellXMov = this.map.getCell(row+i, col);
            Cell cellYMov = this.map.getCell(row, col+i);
            if (i != 0){ // if pos checked is out of bound, ignored it
                if (cellYMov == null || cellXMov == null){
                    continue;
                }
            }
            if (cellYMov.getShip() != null || cellXMov.getShip() != null){ // if ship in pos checked, raise exception
                throw new Exception("The position is not valid");
            }
        }

    }
    private void HelperPlaceShip(int row,int col, ArrayList<Cell> validCells) throws Exception{ //Se puede mejorar pq checkea mas de una vez a ciertas celdas
        for(int i = -1; i < 2; i++ ){ //Checks one position to its right,left,up,down
            Cell cellXMov = this.map.getCell(row+i, col);
            Cell cellYMov = this.map.getCell(row, col+i);
            if (i != 0){ // if pos checked is out of bound, ignored it
                if (cellYMov == null || cellXMov == null){
                    continue;
                }
            }
            if (cellYMov.getShip() != null || cellXMov.getShip() != null){ // if ship in pos checked, raise exception
                throw new Exception("The position is not valid");
            }
        }
        validCells.add(this.map.getCell(row,col));

    }
    public String shoot(@NotNull String pos, @NotNull Player p2){ // String like "A6" expected
        Cell cellP1 = this.map.getCell(pos);
        Cell cellP2 = p2.getMap().getCell(pos);
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
        return "Position was already shot ";
    }

    public String shoot(String pos, Ship ship, Player p2){
        return "x";
    }

    public void showMap(){
        // Top Number Legend
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < this.map.getBoard().length + 1; i++){
            if (i == 0){
                System.out.print("   ");
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
        System.out.println();
        for (int i = 0; i < this.map.getBoard().length + 1; i++){
            if (i == 0){
                System.out.print("   ");
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
                    System.out.print("| � ");
                }
                else {
                    System.out.print("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }
}
