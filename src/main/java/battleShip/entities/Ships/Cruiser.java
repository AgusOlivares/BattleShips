package battleShip.entities.Ships;

import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

public class Cruiser extends Ship {
    public Cruiser() {
        super(4);
    }


    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {
        return null;
    }


}
