package battleShip.entities;


import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

/**
 * Clase padre de todos los tipos de barcos
 * Todos los barcos son creados con una longitud fija, una lista con las celdas que ocupan, un indicador de estado vivo/muerto,
 * y un costo fijo de habilidad.
 * @version 1.2, 08/09/2023
 * @author Agustin Olivares
 */
public abstract class Ship extends MapElement{
    protected final int length;
    protected ArrayList<Cell> occupiedCells;
    protected boolean sunken;
    protected final int abilityCost;


    /**
     * Construye el barco segun su longitud y asigna el costo de habilidad correspondiente
     * @param length
     */
    // Constructors
    public Ship(int length) {
        this.length = length;
        this.occupiedCells = new ArrayList<>();
        this.sunken = false;
        switch (length) {
            case 2: 
                this.abilityCost = 3;
                break;
            case 3: 
                this.abilityCost = 5;
                break;
            case 4: 
                this.abilityCost = 7;
                break;
            case 5: 
                this.abilityCost = 9;
                break;
            default: 
                this.abilityCost = 0;
                break;
        }
    }


    // Getters
    public int getLength() {
        return length;
    }
    public ArrayList<Cell> getOccupiedCells() {
        return occupiedCells;
    }
    public Boolean getSunken(){ return sunken;}
    public int getAbilityCost() {
        return abilityCost;
    }


    //Setters
    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }
    public void setOccupiedCells(Cell cell){
        this.occupiedCells.add(cell);
    }


    //Methods
    /**
     * Se encarga de actualizar el estado 'sunken' del barco
     * Si se disparó en todas las celdas que este ocupa, se indica que fue hundido y se cambia su estado
     */
    public void wasShot(){
        System.out.println("Un barco enemigo fue golpeado");
        int shootedCells = 0;
        for (Cell cell: occupiedCells){
            if (cell.getWasShot()){
                ++shootedCells;
            }
        };
        if (shootedCells == length) {
            System.out.println("Un barco enemigo fue hundido");
            this.setSunken(true);
        }
    }

    /**
     * Al utilizar la habilidad de un barco, Este metodo se encarga de revisar que las cargas que el jugador tiene disponibles sean suficientes.
     * @param player Jugador que solicita usar el metodo
     * @return true si la cantidad de cargas es suficiente y se le descuentan de las cargas disponibles, false en caso contrario
     */
    public Boolean useCharges(Player player){
        int remainingCharges = player.getCharges() - this.abilityCost;
        if (remainingCharges < 0){
            return false;
        }
        player.setCharges(remainingCharges);
        return true;
    }
    
    public abstract Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy);

}
