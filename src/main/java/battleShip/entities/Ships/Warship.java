package battleShip.entities.Ships;

import battleShip.entities.Player;
import battleShip.entities.Ship;
import org.jetbrains.annotations.NotNull;

public class Warship extends Ship {

    public Warship() {
        super(3);
    }


    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, @NotNull Player Enemy) {
        return null;
    }


}
