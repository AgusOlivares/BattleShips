package battleShip.entities.Ships;

import battleShip.entities.Ship;
import battleShip.entities.SpecialShip;

public class Submarine extends Ship implements SpecialShip {

    public Submarine() {
        super(2);
    }

    @Override
    public Boolean SpecialAbility() {
        return null;
    }


}
