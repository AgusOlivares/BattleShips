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


    // Constructors
    /**
     * Construye el barco segun su longitud y asigna el costo de habilidad correspondiente
     * @param length int
     */
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
    /**
     * Retorna el largo del barco
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * Retorna el atributo 'sunken', un boleano segun el barco esta hundido o no
     * @return Boolean
     */
    public Boolean getSunken(){ return sunken;}

    /**
     * Retorna el atributo 'abilityCost', coste de la habilidad del barco
     * @return int
     */
    public int getAbilityCost() {
        return abilityCost;
    }


    //Setters
    /**
     * Actualiza el valor del atributo 'sunken'
     * @param sunken Boolean
     */
    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }

    /**
     * Agrega un objeto Cell al final de la coleccion 'occupiedCells'
     * @param cell Cell
     */
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
     */
    public void useCharges(Player player){
        int remainingCharges = player.getCharges() - this.abilityCost;
        if (remainingCharges < 0){
            return;
        }
        player.setCharges(remainingCharges);
    }

    /**
     * Metodo abstracto para que cada subclase de 'Ship' implemente la habilidad especial del barco.
     * @param player Player
     * @param pos String
     * @param Enemy Player
     * @return Boolean
     */
    public abstract Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy);

}
