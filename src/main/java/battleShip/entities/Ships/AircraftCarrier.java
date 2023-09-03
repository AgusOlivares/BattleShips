package battleShip.entities.Ships;

import battleShip.entities.Ship;
import battleShip.entities.SpecialShip;

public class AircraftCarrier extends Ship implements SpecialShip {
    public AircraftCarrier() {
        super(5);
    }

    @Override
    public Boolean useAbility() {
        // Select "v" to shoot one space up and one down from the original shootplace
        // Select "h" to shoot on the left and rightside from the original shootplace
        // Implement aircraftCarrier ability

        return null;
    }
}