package battleShip.entities;

public abstract class Ship {
    protected int lenght;
    protected String[] occupiedCells;
    protected String[] shotedCells;
    protected boolean sunken;

    // Aca, los constructores y getter/setter deberian ser protected?

    public Ship(int lenght, String[] occupiedCells) {
        this.lenght = lenght;
        this.occupiedCells = occupiedCells; // Esto no se va a poder inicializar como tal si no que va a tener que ser construido
        this.shotedCells = new String[this.lenght]; // Esto se tiene que inicializar como una lista vacia o podria iniciar como un array estatico con la longitud de la lista
        this.sunken = false;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String[] getOccupiedCells() {
        return occupiedCells;
    }

    public void setOccupiedCells(String[] occupiedCells) {
        this.occupiedCells = occupiedCells;
    }

    public String[] getShotedCells() {
        return shotedCells;
    }

    public void setShotedCells(String[] shotedCells) {
        this.shotedCells = shotedCells;
    }

    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }

    public void isSunken(){
        // change sunken atribute when the ship is destroyed
    }
}
