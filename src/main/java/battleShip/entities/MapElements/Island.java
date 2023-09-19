package battleShip.entities.MapElements;

import battleShip.entities.Cell;
import battleShip.entities.MapElement;

public class Island extends MapElement {
    private Cell position;

    //Contructor
    public Island(Cell position){
        this.position = position;
        position.setElement(this);
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
        System.out.println("Contacto con una isla enemegia. ¿Isla enemiga?");
    }
}
