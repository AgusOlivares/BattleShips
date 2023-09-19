package battleShip.entities.MapElements;

import battleShip.entities.Cell;
import battleShip.entities.MapElement;

public class Water extends MapElement {
    private Cell position;

    //Contructor
    public Water(Cell position){
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
        System.out.println("¡Agua!");
    }
}
