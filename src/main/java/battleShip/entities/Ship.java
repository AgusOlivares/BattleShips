package battleShip.entities;

import java.util.ArrayList;

public abstract class Ship extends MapElement {
    protected final int length;
    protected ArrayList<Cell> occupiedCells;
    protected boolean sunken;
    protected final int abilityCost;

    // Constructors
    public Ship(int length) {
        this.length = length;
        this.occupiedCells = new ArrayList<>();
        this.sunken = false;
        this.abilityCost = switch (length) {
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 7;
            case 5 -> 9;
            default -> 0;
        };
    }

    // Getters
    public int getLength() {
        return length;
    }
    public ArrayList<Cell> getOccupiedCells() {
        return occupiedCells;
    }
    public Boolean getSunken(){ return sunken;}


    //Setters
    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }
    public void setOccupiedCells(Cell cell){
        this.occupiedCells.add(cell);
    }


    //Methods

    // Checks the cells the ship is occupying and they are all shot, set the sunken attribute to True
    public void wasShot(){
        System.out.println("Enemy ship was hit");
        int shootedCells = 0;
        for (Cell cell: occupiedCells){
            if (cell.getWasShot()){
                ++shootedCells;
            }
        };
        if (shootedCells == length) {
            System.out.println("An enemy ship was sunken");
            this.setSunken(true);
        }
    }


    public Boolean useCharges(Player player){
        int remainingCharges = player.getCharges() - this.abilityCost;
        if (remainingCharges < 0){
            return false;
        }
        player.setCharges(remainingCharges);
        return true;
    }

    public abstract Boolean useAbility();

}
