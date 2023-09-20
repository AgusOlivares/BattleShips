/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleShip.entities.Ships;


import battleShip.Interface.SpecialShipInterface;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;
/**
 * Una clase encargada de crear una Lancha ('Boat') Subclase de 'Ship'.
 * @see Ship
 * @see SpecialShipInterface
 * @author Agustin Olivares
 * @version 1.4, 08/09/2023
 */
public class Boat extends Ship {

    /**
     * Construye un objeto Ship, inicializando el atributo 'length' en 1.
     */
    public Boat() {
        super(1); // on init boat length will be 1
    }

    /**
     * @return null
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy) {
        return null;
    }


}
