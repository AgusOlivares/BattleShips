package battleShip.entities;

public abstract class Ship {
    protected int length;

    // protected Player ownerPlayer; // Asociates class Player and Ship
    protected Cell[] occupiedCells;
    protected boolean sunken;

    // Constructors
    public Ship(int length) { // remember to add ownerPlayer idea
        this.length = length;
        this.occupiedCells = new Cell[length];
        for (int i = 0; i < this.occupiedCells.length; i++ ){
            this.occupiedCells[i] = new Cell();
        }
        this.sunken = false;
        //this.ownerPlayer = ownerPlayer;
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

    //public Player getOwnerPlayer() {
    //    return ownerPlayer;
    //}

    //Setters
    public void setLength(int length) {
        this.length = length;
    }
    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }

    //public void setOwnerPlayer(Player ownerPlayer) {
    //    this.ownerPlayer = ownerPlayer;
    //}

    //Methods
    public void isSunken(){ // change sunken atribute when the ship is destroyed
        int shootedCells = 0;
        for (Cell cell: occupiedCells){
            if (cell.getWasShot()){
                ++shootedCells;
            }
        };
        if (shootedCells == length) this.setSunken(true);
    }
    public String placeShip(String firstPosition, String lastPosition){ //Strings like "A6", "A8" expected

        int xFirst = firstPosition.substring(0,1).charAt(0) - 'A';
        int yFirst = Integer.parseInt(firstPosition.substring(1)) - 1;
        int xLast = lastPosition.substring(0,1).charAt(0) - 'A';
        int yLast = Integer.parseInt(lastPosition.substring(1)) - 1;


        // Checking first position in board
        if ((xFirst < 0 || xFirst > 9) || (yFirst < 0 || yFirst > 9)){
            return "First position out of board";
        }

        // Checking last position in board
        if ((xLast < 0 || xLast > 9) || (yLast < 0 || yLast > 9)){
            return "Last position out of board";
        }

        // Cheking lenght of positions with ship lenght
        if (!((xLast - xFirst == length) && (yFirst == yLast))){
            return "Invalid positions";
        } else if ((yLast - yFirst == length) && (xFirst == xLast)) {
            return "Invalid positions";
        }
        // add more conditions to check
        return "boat placed correctly";
    }
}
