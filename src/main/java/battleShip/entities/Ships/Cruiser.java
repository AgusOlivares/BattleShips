package battleShip.entities.Ships;

import battleShip.entities.Cell;
import battleShip.entities.Game;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 *
 */
public class Cruiser extends Ship{
    public Cruiser() {
        super(4);
    }


    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {
        
        Game auxGame = new Game();
        boolean wasShotSuccessful = false;
        do {            
            wasShotSuccessful = player.shoot(pos, Enemy);
            if(!wasShotSuccessful){
                System.out.println("Incorrecto, intente de nuevo");
            }
        } while (!wasShotSuccessful);
        wasShotSuccessful = false;
        
        System.out.println("A continuación realizarás el segundo disparo");
        do {            
            String position = auxGame.askCoordinates();
            wasShotSuccessful = player.shoot(position, Enemy);
            if(!wasShotSuccessful){
                System.out.println("Incorrecto, intente de nuevo");
            }
        } while (!wasShotSuccessful);
        
        return true;
    }

    @Override
    public Void showAbility() {

        System.out.println("Doble disparo: ");
        System.out.println("La habilidad especial del Crucero permite realizar dos disparos normales seguidos en las posiciones seleccionadas");

        return null;
    }

}
