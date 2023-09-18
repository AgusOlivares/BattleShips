package battleShip.entities.Ships;

import battleShip.entities.Cell;
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

        boolean posibbleShoot = false;
        shoot(player, pos, Enemy);
        Scanner read = new Scanner(System.in);
        while (!posibbleShoot) {
            System.out.println("Selecciona una posicion para el segundo disparo");
            String newPos = read.next();
            if (Enemy.getMap().getCell(newPos) != null){
                shoot(player, newPos, Enemy);
                posibbleShoot = true;
            } else {
                System.out.println("La posicion es invalida, intenta nuevamente");
            }
        }
        return true;
    }

    @Override
    public Void showAbility() {

        System.out.println("Doble disparo: ");
        System.out.println("La habilidad especial del Crucero permite realizar dos disparos normales seguidos en las posiciones seleccionadas");

        return null;
    }
    private Boolean shoot(Player player, String pos, Player Enemy) { // String like "A6" expected
        Cell cellP1 = player.getMap().getCell(pos);
        Cell cellP2 = Enemy.getMap().getCell(pos);
        try {
            cellP2.shot(); //shot p2
            player.getMap().addShotCell(cellP1);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
