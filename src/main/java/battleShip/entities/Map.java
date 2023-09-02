package battleShip.entities;

import org.jetbrains.annotations.NotNull;

public class Map {
    private Cell[][] board;
    // set de posiciones ocupadas q posean la celda Cell
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
    public Cell getCell(@NotNull String pos){
        int x = pos.substring(0,1).charAt(0) - 'A';
        int y = Integer.parseInt(pos.substring(1)) - 1;
        try {
            return this.board[x][y];
        }catch (Exception e){
            return null;
        }
    }
    public Cell getCell( int row,  int col){
        try {
            return this.board[row][col];
        }catch (Exception e){
            return null;
        }

    }

}
