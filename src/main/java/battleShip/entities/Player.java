package battleShip.entities;

import battleShip.entities.MapElements.Island;
import battleShip.entities.Ships.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.Math.abs;
/**
 * Una clase para representar al jugador
 * Cada jugador es creado junto con un nombre, un mapa, una lista de barcos asociados y un numero de cargas
 * @version 2.4, 19/9/2023
 * @author Martin Farrés
 */
public class Player {
    private final String name;
    private final ArrayList<Ship> ships;
    private final Map map;
    private int charges;


    // Constructors
    /**
     * Construye un jugador a partir de su nombre
     *
     * @param name El nombre del jugador.
     */
    public Player(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.ships.add(new Boat());
        this.ships.add(new Submarine());
        this.ships.add(new Warship());
        this.ships.add(new Cruiser());
        this.ships.add(new AircraftCarrier());
        this.map = new Map();
        this.charges = 10;
    }


    //Getters
    /**
     * Retorna el nombre del jugador
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna la coleccion de barcos del jugador
     * @return ArrayList<Ship>
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Retorna el objeto Map del jugador
     * @return Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Retorna las cargas del jugador
     * @return charges
     */
    public int getCharges() {
        return charges;
    }


    //Setters
    /**
     * Establece las cargas del jugador
     * @param charges
     */
    public void setCharges(int charges) {
        this.charges = charges;
    }


    //Methods
    /**
     * Checkea que sea posible colocar el barco y de ser posible lo asocia en las celdas indicadas
     * Ejemplo de input: ("A5", "A7", Submarine)
     *
     * @param startPos Posicion inicial donde se coloca la cabecilla del barco.
     * @param finalPos Posicion final donde termina el barco
     * @param ship     Tipo de barco colocado
     * @return true si la posicion es valida y se coloco exitosamente, o false si la posicion
     * no es valida y levanta una excepcion
     */
    public boolean placeShip(@NotNull String startPos, @NotNull String finalPos, @NotNull Ship ship) {
        int row = startPos.substring(0, 1).charAt(0) - 'A';
        int col = Integer.parseInt(startPos.substring(1)) - 1;
        int rowFinal = finalPos.substring(0, 1).charAt(0) - 'A';
        int colFinal = Integer.parseInt(finalPos.substring(1)) - 1;
        ArrayList<Cell> validCells = new ArrayList<>();

        try {
            // Check it is not out of bounds
            if ((this.map.getCell(startPos) == null) || (this.map.getCell(finalPos) == null)) {
                throw new Exception("Posición inválida");
            }

            // Check it is not diagonal
            if (abs(row - rowFinal) > 0 && abs(col - colFinal) > 0) {
                throw new Exception("No puedes colocar el barco en forma diagonal");
            }

            // Checks length of boat is the same as length of placement
            if ((abs(row - rowFinal) + abs(col - colFinal)) != ship.length - 1) {
                throw new Exception("La posición seleccionada debería tener la siguiente longitud: " + ship.length);
            }

            // Are all the position selected available?
            if (abs(row - rowFinal) > 0) { // Movement in should be in row or col?
                for (int j = row; j <= rowFinal; j++) { // Checks all positions are available, if not raises exception
                    helperPlaceShip(j, col, validCells);
                }
            } else if (abs(col - colFinal) > 0) {
                for (int j = col; j <= colFinal; j++) {
                    helperPlaceShip(row, j, validCells);
                }
            } else {
                helperPlaceShip(row, col, validCells);
            }

            //Place ship inside cell
            for (Cell cell : validCells) {
                cell.setElement(ship);
                ship.setOccupiedCells(cell);
            }

        } catch (Exception e) {
            System.out.printf("%s", e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Itera por las casillas colindantes chequeando que no haya ningún barco ni se encuentre sobre uno o una isla.
     * De ser asi, retorna la coleccion 'validCells' con dicha celda incluida
     * Ejemplo de input: (2, 2, {Cell (2,3), Cell (4,5),...})
     * @param row posicion de la fila
     * @param col posicion de la columna
     * @param validCells coleccion de las celdas q son validas
     * @throws Exception si la posicion no es valida
     */
    private void helperPlaceShip(int row, int col, ArrayList<Cell> validCells) throws Exception { //Se puede mejorar pq checkea mas de una vez a ciertas celdas
        for (int i = -1; i < 2; i++) { //Checks one position to its right,left,up,down
            Cell cellXMov = this.map.getCell(row + i, col);
            Cell cellYMov = this.map.getCell(row, col + i);

            if (i != 0) {
                if (cellXMov != null) {// if pos checked is out of bound, ignored it
                    if ((cellXMov.getElement() instanceof Ship) ) { // if ship in pos checked, raise exception
                        throw new Exception("La posicion no es valida");
                    }
                } else if (cellYMov != null) {// if pos checked is out of bound, ignored it
                    if ((cellYMov.getElement() instanceof Ship)) {
                        throw new Exception("La posicion no es valida");
                    }
                }
            }else{
                if (cellXMov != null) {// if pos checked is out of bound, ignored it
                    if ((cellXMov.getElement() instanceof Island) || (cellXMov.getElement() instanceof Ship)) { // if island in pos checked, raise exception
                        throw new Exception("La posicion no es valida");
                    }
                }
            }

        }
        validCells.add(this.map.getCell(row, col));

    }

    /**
     * Obtiene la celda del mapa enemigo de dicha posicion, llama al metodo shot() del objeto Cell y llama al
     * metodo addShotCell() del objeto Map del jugador.
     * Ejemplo de input: ("B9", Player() p2)
     * @param pos Posicion donde se quiere disparar
     * @param p2 Objeto Player del enemigo
     * @return true si el disparo fue efectuado con exito, false si el disparo no se puede realizar
     */
    public Boolean shoot(@NotNull String pos, @NotNull Player p2) { // String like "A6" expected
        Cell cellP1 = this.map.getCell(pos);
        Cell cellP2 = p2.getMap().getCell(pos);
        try {
            cellP2.shot(); //shot p2
            this.map.addShotCell(cellP1);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * De tener la cantidad de cargas suficientes, utiliza dichas cargas y llama al metodo useAbility() del objeto ship.
     * @param pos posicion que se desea disparar
     * @param enemy Objeto player del enemigo
     * @param ship barco con el que se quiere disparar
     * @return true si el disparo fue efectuado, false si no se puede realizar.
     */
    public Boolean shootAbility(String pos, Player enemy, Ship ship) {
        if(ship.useAbility(this, pos, enemy)){
            ship.useCharges(this);
            return true;
        }
        return false;

    }
}