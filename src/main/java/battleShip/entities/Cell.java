package battleShip.entities;

public class Cell {
    private String element;
    private Boolean wasShot;
    private Ship ship;

    // Constructor
    public Cell(){
        //Initializes all cells as water elements
        this.element = " ";
        this.wasShot = false;
        this.ship = null;
    }
    //Getters
    public String getElement() {
        return element;
    }
    public Boolean getWasShot() {
        return wasShot;
    }
    public Ship getShip() {
        return ship;
    }
    //Setters
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    public void setElement(String element) {
        this.element = element;
    }
    public void setWasShot(Boolean wasShot) {
        this.wasShot = wasShot;
    }

    //Methods
}
