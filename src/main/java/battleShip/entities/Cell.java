package battleShip.entities;

public class Cell {
    private String element;
    private Boolean wasShot;
    private Boat boat;

    // Constructor
    Cell(){
        //Initializes all cells as water elements
        this.element = " ";
        this.wasShot = false;
        this.boat = null;
    }
    //Getters
    public String getElement() {
        return element;
    }
    public Boolean getWasShot() {
        return wasShot;
    }
    public Boat getBoat() {
        return boat;
    }
    //Setters
    public void setBoat(Boat boat) {
        this.boat = boat;
    }
    public void setElement(String element) {
        this.element = element;
    }
    public void setWasShot(Boolean wasShot) {
        this.wasShot = wasShot;
    }

    //Methods
}
