package battleShip.entities;

public abstract class Ship {
    protected int length;
    protected Cell[] occupiedCells;
    protected boolean sunken;

    // Constructors
    public Ship(int length) {
        this.length = length;
        this.occupiedCells = new Cell[length];
        for (int i = 0; i < this.occupiedCells.length; i++ ){
            this.occupiedCells[i] = new Cell();
        }
        this.sunken = false;
    }

    // Getters
    public int getLength() {
        return length;
    }
    public Cell[] getOccupiedCells() {
        return occupiedCells;
    }
    public Boolean getSunken(){
        return sunken;
    }

    //Setters
    public void setLength(int length) {
        this.length = length;
    }
    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }

    //Methods
    public void isSunken(){
        // change sunken atribute when the ship is destroyed
    }
}
