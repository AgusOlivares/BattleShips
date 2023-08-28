package battleShip.entities;

public class Map {
    private Cell[][] board;
    //Constructors
    Map(){
        this.board = new Cell[9][9];
    }
    //Getters
    public Cell[][] getBoard() {
        return board;
    }
    //Setters
    public void setBoard(Cell[][] board) {
        this.board = board;
    }
    //Methods


}
