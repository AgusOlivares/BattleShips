package battleShip.entities;

import javax.swing.text.Position;

public class Decoy extends MapElement{
    private Cell position;

    //Contructor
    public Decoy(Cell position){
        this.position = position;
    }

    //Setters
    public void setPosition(Cell position) {
        this.position = position;
    }

    //Getters
    public Cell getPosition() {
        return position;
    }

    //Methods
    @Override
    public void wasShot() {
        System.out.println("Un barco enemigo fue golpeado");
    }
}
