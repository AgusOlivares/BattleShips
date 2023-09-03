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
    public Boolean shot() throws Exception{
        if (this.wasShot){ // already shot, raise exception
            throw new Exception("Position already shot");
        }else {
            this.setWasShot(true);
            if (this.ship != null){ // is there a ship, check if it's not sunken
                System.out.println("Enemy ship was hit");
                this.ship.isSunken();
                return true;
            }
            System.out.println("Water was hit");
            return false; // is water
        }
    }
}
