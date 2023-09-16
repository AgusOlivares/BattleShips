package battleShip.entities.Ships;

import battleShip.entities.Cell;
import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 *
 */
public class Cruiser extends Ship {
    public Cruiser() {
        super(4);
    }


    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {

        boolean posibbleShoot = false;
        shoot(player, pos, Enemy);
        Scanner read = new Scanner(System.in);
        while (posibbleShoot != true){
            System.out.println("Select 2nd position to shoot");
            String newPos = read.next();
            try{
                shoot(player, newPos, Enemy);
                posibbleShoot = true;
            } catch (Exception e){
                System.out.println("Invalid position, try again");
            }
        }
        return true;
    }

    private Boolean shoot(Player player, String pos, Player Enemy) { // String like "A6" expected
        Cell cellP1 = player.getMap().getCell(pos);
        Cell cellP2 = Enemy.getMap().getCell(pos);
        // agregar condiciones para evitar errores
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
