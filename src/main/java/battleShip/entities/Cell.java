package battleShip.entities;

import battleShip.entities.MapElements.Water;

public class Cell {
    private MapElement element;
    private Boolean wasShot;

    // Constructor
    public Cell(){
        //Initializes all cells as water elements
        this.element = new Water(this);
        this.wasShot = false;
    }
    //Getters
    public MapElement getElement() {
        return element;
    }
    public Boolean getWasShot() {
        return wasShot;
    }

    //Setters
    public void setElement(MapElement element) {
        this.element = element;
    }
    public void setWasShot(Boolean wasShot) {
        this.wasShot = wasShot;
    }

    //Methods

    // Returns true if there is contact with ship, calls method Ship.isSunken()
    // Returns false if there is no contact
    // if cell has already been hit, raise exception
    public void shot() throws Exception{
        if (this.wasShot){ // already shot, raise exception
            throw new Exception("Position already shot");
        }else {
            this.setWasShot(true);
            this.element.wasShot();
        }
    }
}
