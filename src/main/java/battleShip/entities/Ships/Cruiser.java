package battleShip.entities.Ships;

import battleShip.entities.Ship;
import battleShip.entities.SpecialShip;

public class Cruiser extends Ship implements SpecialShip {
    public Cruiser() {
        super(4);
    }


    @Override
    public Boolean SpecialAbility() {
        return null;
    }


}
