package battleShip.entities;

import battleShip.entities.MapElements.Water;

/**
 * La clase Cell, representa a las celdas del tablero. Cada objeto almacena el elemento que se encuentra en dicha celda
 * y si ha sido disparada o no.
 * @see MapElement para mas informacion acerca del elemento
 * @version 2.1, 8/9/2023
 * @author Martin Farres
 */
public class Cell {
    private MapElement element;
    private Boolean wasShot;


    // Constructor
    /**
     * Construye a un objeto Cell, inicializandolo con un objeto Water como su elemento
     * y false, porque todavia no ha sido disparada.
     */
    public Cell(){
        //Initializes all cells as water elements
        this.element = new Water(this);
        this.wasShot = false;
    }


    //Getters
    /**
     * Retorna el elemento dentro de la celda
     * @return MapElement
     */
    public MapElement getElement() {
        return element;
    }

    /**
     * Retorna un booleano indicando si ha ya ha sido disparada
     * @return true si ha fue disparada, false si no fue disparada
     */
    public Boolean getWasShot() {
        return wasShot;
    }


    //Setters
    /**
     * Actualiza el atributo 'element'
     * @param element MapElement
     */
    public void setElement(MapElement element) {
        this.element = element;
    }

    /**
     * Actualiza el atributo 'wasShot'
     * @param wasShot
     */
    public void setWasShot(Boolean wasShot) {
        this.wasShot = wasShot;
    }


    //Methods
    /**
     * Si la celda no ha sido disparada, setea wasShot a verdadero y llama el metodo wasShot() del objeto MapElement.
     * @throws Exception si la posicion ya ha sido disparada, lanza una excepcion.
     */
    public void shot() throws Exception{
        if (this.wasShot){ // already shot, raise exception
            throw new Exception("Position already shot");
        }else {
            this.setWasShot(true);
            this.element.wasShot();
        }
    }
}
