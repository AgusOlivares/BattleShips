package battleShip.entities;

import battleShip.interfaces.SpecialShipInterface;

import java.util.ArrayList;
import battleShip.interfaces.SpecialShipInterface;
import org.jetbrains.annotations.NotNull;

/**
 * Clase padre de todos los tipos de barcos
 * Todos los barcos son creados con una longitud fija, una lista con las celdas que ocupan, un indicador de estado vivo/muerto, y un costo fijo de habilidad.
 * @version 1.2, 08/09/2023
 * @author Agustin Olivares
 */
public abstract class Ship extends MapElement implements SpecialShipInterface{
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
        this.abilityCost = switch (length) {
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 7;
            case 5 -> 9;
            default -> 0;
        };
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

    /**
     * Metodo polimorfico para utilizar la habilidad especial del barco
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario.
     * @return True si se pudo activar correctamente la habilidad
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy) {
        return null;
    }

    @Override
    public Void showAbility() {
        return null;
    }

}
