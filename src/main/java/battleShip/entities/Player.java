package battleShip.entities;

import battleShip.entities.Ships.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Player {
    private final String name;
    private ArrayList<Ship> ships;
    private final Map map;
    private int charges;

    //Constructor
    public Player(String name){
        this.name = name;

        this.ships = new ArrayList<>(); // <? extends Ship>
        this.ships.add(new Boat());
        this.ships.add(new Cruiser());
        this.ships.add(new Submarine());
        this.ships.add(new Warship());
        this.ships.add(new AircraftCarrier());

        this.map = new Map();
        this.charges = 0;
    }

    //Getters
    public String getName() {
        return name;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Map getMap() {
        return map;
    }
    public int getCharges() {
        return charges;
    }

    //Setters
    public void setCharges(int charges) {
        this.charges = charges;
    }

    //Methods

    // Example of input: ("A5", "A7", Submarine)
    // Returns true if position is valid and associates the ship passed to Cell.Ship() attribute in each cell
    // Returns false if not possible and raises exception for each possible error
    public boolean placeShip(@NotNull String startPos,@NotNull String finalPos, @NotNull Ship ship){
        int row = startPos.substring(0,1).charAt(0) - 'A';
        int col = Integer.parseInt(startPos.substring(1)) - 1;
        int rowFinal = finalPos.substring(0,1).charAt(0) - 'A';
        int colFinal = Integer.parseInt(finalPos.substring(1)) - 1;
        ArrayList<Cell> validCells = new ArrayList<>();

        try{
            // Check it is not out of bounds
            if ((this.map.getCell(startPos) == null) || (this.map.getCell(finalPos) == null)){
                throw new Exception("The position is out of bounds");
            }

            // Check it is not diagonal
            if (abs(row-rowFinal) > 0 && abs(col-colFinal) > 0){
                throw new Exception("The Position can't be diagonal");
            }

            // Checks length of boat is the same as length of placement
            if ((abs(row-rowFinal) + abs(col-colFinal)) != ship.length-1){
                throw  new Exception("The position selected should support the ships length: " + ship.length);
            }

            // Are all the position selected available?
            if (abs(row-rowFinal) > 0){ // Movement in should be in row or col?
                for (int j = row; j <= rowFinal; j++) { // Checks all positions are available, if not raises exception
                    HelperPlaceShip(j, col, validCells);
                }
            }else if (abs(col-colFinal)>0){
                for (int j = col; j <= colFinal; j++){
                    HelperPlaceShip(row, j, validCells);
                }
            }else {
                HelperPlaceShip(row, col, validCells);
            }

            //Place ship inside cell
            for(Cell cell : validCells){
                cell.setShip(ship);
                ship.setOccupiedCells(cell);
            }

        }catch (Exception e){
            System.out.printf("%s", e);
            return false;
        }

        return true;
    }

    // Example Input: (2, 2, {Cell (2,3), Cell (4,5),...})
    // Checks up, down, right and left in every cell for ships, and if all cells are empty the cell is added to the ArrayList
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

    // Input Example: ("B9", Player() p2)
    // Returns true if the shot can be shot, sets the element to the p1 map and calls the methods Cell.shot()
    // Returns false if shot cant be done and raises exception
    public Boolean shoot(@NotNull String pos, @NotNull Player p2){ // String like "A6" expected
        Cell cellP1 = this.map.getCell(pos);
        Cell cellP2 = p2.getMap().getCell(pos);
        try{
            Boolean shotP2 = cellP2.shot(); //shot p2
            if (shotP2){
                cellP1.setElement("X");
            }
            else{
                cellP1.setElement("O");
            }
            return true;

        }catch (Exception e){
            System.out.printf("%s", e);
            return false;
        }
    }

    public String shoot(String pos, Ship ship, Player p2){
        return "x";
    } // ?????????


    // Prints Matrix to console with the annotation of the player shots
    public void showMap(){
        // Top Number Legend
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < this.map.getBoard().size() + 1; i++){
            if (i == 0){
                System.out.print("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < this.map.getBoard().size(); i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < this.map.getBoard().get(0).size(); j++){
                // Indicators
                System.out.printf("| %s ", map.getCell(i,j).getElement());
            }
            System.out.println(" |");
        }
        System.out.println();
    }


    // Prints Matrix to console with the player boats and if their are hit
    public void showMapShip(){
        // Top Number Legend
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < this.map.getBoard().size() + 1; i++){
            if (i == 0){
                System.out.print("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < this.map.getBoard().size(); i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < this.map.getBoard().get(0).size(); j++){
                // Indicators
                if (map.getCell(i,j).getShip() != null ){
                    if (!map.getCell(i, j).getShip().getSunken()){
                        System.out.print("| � ");
                    }else if(map.getCell(i, j).getShip().getSunken()){
                        System.out.print("| X ");
                    }
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
