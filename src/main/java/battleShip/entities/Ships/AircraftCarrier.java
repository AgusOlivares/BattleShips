package battleShip.entities.Ships;

import battleShip.entities.Ship;
import battleShip.entities.SpecialShip;

public class AircraftCarrier extends Ship implements SpecialShip {
    public AircraftCarrier() {
        super(5);
    }


    @Override
    public Boolean SpecialAbility() {
        return null;
    }


}