package battleShip.entities;

public class Mine extends MapElement{
    private Cell position;

    //Contructor
    Mine(){
        this.position = null;
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
    public void wasShot(){
    }
    public Boolean place(Cell cell){
        if (cell.getElement() != null){
            return false;
        }
        setPosition(cell);
        cell.setElement(this);
        return true;
    }
}
