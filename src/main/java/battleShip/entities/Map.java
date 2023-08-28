package battleShip.entities;

public class Map {
    private Cell[][] board;
    //Constructors
    public Map(){
        this.board = new Cell[10][10];
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = new Cell();
            }
        }
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
