package battleShip.entities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * La clase Map, representa el tablero de cada jugador con una matriz de 10x10,
 * donde cada celda posee un objeto Cell dentro, y almacena en una coleccion 'HashSet' las celdas enemigas que el jugador
 * ha disparado
 * @see Cell para mas informacion acerca de las celdas
 * @version 2.1, 8/9/2023
 * @author Martin Farrés
 */
public class Map {
    private final ArrayList<ArrayList<Cell>> board;
    private Set<Cell> shotCells;


    //Constructors
    /**
     * Construye el mapa inicializando un objeto Cell en cada posicion del tablero y, el hashSet de posiciones disparadas
     */
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
    /**
     * Retorna el tablero
     * @return board
     */
    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    /**
     * Retorna la coleccion de celdas que el jugador ha disparado
     * @return shotCells
     */
    public Set<Cell> getShotCells() {
        return shotCells;
    }


    //Methods
    /**
     * Devuelve el objeto Cell que se encuentra en dicha posición, dada la posicion como String.
     * Ejemplo de input: ("A8")
     * @param pos posicion de la celda ha obtener
     * @return Cell si se encuentra, null si no se ha podido obtener
     */
    public Cell getCell(@NotNull String pos){
        int x = pos.substring(0,1).charAt(0) - 'A';
        int y = Integer.parseInt(pos.substring(1)) - 1;
        try {
            return this.board.get(x).get(y);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Devuelve el objeto Cell que se encuentra en dicha posición, dada con valores numericos de la fila y la columna.
     * Ejemplo de input: (5, 6)
     * @param row numero de fila (0-9)
     * @param col numero de columna (0-9)
     * @return Cell si se encuentra, null si no se ha podido obtener
     */
    public Cell getCell( int row,  int col){
        try {
            return this.board.get(row).get(col);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Agrega, si no se le ha disparado antes, la celda que ha sido disparada por el jugador a la coleccion ‘shotCells’.
     * @param cell Objeto Cell
     * @return true si no se ha disparado a dicha celda con anterioridad, falso si ya se le ha disparado
     */
    public Boolean addShotCell(Cell cell){
        return this.shotCells.add(cell);
    }

    /**
     * Cheque que dicha celda se encuentre en la coleccion ‘shotCells’.
     * @param cell Objeto Cell
     * @return true si ya ha sido disparada, false si no ha sido disparada
     */
    public Boolean isCellShot(Cell cell){
        return this.shotCells.contains(cell);
    }

}
