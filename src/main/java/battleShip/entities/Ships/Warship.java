package battleShip.entities.Ships;

import battleShip.entities.Ship;
import battleShip.entities.SpecialShip;

public class Warship extends Ship implements SpecialShip {

    public Warship() {
        super(3);
    }


    @Override
    public Boolean useAbility() {
        return null;
    }


}
