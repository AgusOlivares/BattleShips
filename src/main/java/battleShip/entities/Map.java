package battleShip.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Map {
    private final ArrayList<ArrayList<Cell>> board;

    private Set<Cell> shotCells;

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
        this.shotCells = new HashSet<Cell>();
    }

    //Getters
    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    public Set<Cell> getShotCells() {
        return shotCells;
    }
    //Setters


    //Methods

    // Input Example: ("A8")
    // Returns Cell from the pos
    // Return Null if out of border
    public Cell getCell(@NotNull String pos){
        int x = pos.substring(0,1).charAt(0) - 'A';
        int y = Integer.parseInt(pos.substring(1)) - 1;
        try {
            return this.board.get(x).get(y);
        }catch (Exception e){
            return null;
        }
    }

    // Input Example: (5, 6)
    // Returns Cell from the pos
    // Return Null if out of border
    public Cell getCell( int row,  int col){
        try {
            return this.board.get(row).get(col);
        }catch (Exception e){
            return null;
        }
    }

    public Boolean addShotCell(Cell cell){
        return this.shotCells.add(cell);
    }

    public Boolean isCellShot(Cell cell){
        return this.shotCells.contains(cell);
    }

}
