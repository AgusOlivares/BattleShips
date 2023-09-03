package battleShip.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Map {
    private final ArrayList<ArrayList<Cell>> board;

    //Constructors
    public Map(){
        this.board = new ArrayList<ArrayList<Cell>>(10);
        for (int i = 0; i < 10; i++){
            ArrayList<Cell> row = new ArrayList<>(10);
            for (int j = 0; j < 10; j++){
                row.add(new Cell());
            }
            this.board.add(row);
        }
    }
    //Getters
    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    //Setters

    //Methods
    public Cell getCell(@NotNull String pos){
        int x = pos.substring(0,1).charAt(0) - 'A';
        int y = Integer.parseInt(pos.substring(1)) - 1;
        try {
            return this.board.get(x).get(y);
        }catch (Exception e){
            return null;
        }
    }
    public Cell getCell( int row,  int col){
        try {
            return this.board.get(row).get(col);
        }catch (Exception e){
            return null;
        }

    }

}
