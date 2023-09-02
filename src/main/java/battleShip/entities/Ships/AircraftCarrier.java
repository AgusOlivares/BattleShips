package battleShip.entities.Ships;


import battleShip.entities.Player;

public class AircraftCarrier extends SpecialShip {
    public AircraftCarrier(String ability) {
        super(5, ability);
    }

    @Override
    public void useAbility() {
        // Select "v" to shoot one space up and one down from the original shootplace
        // Select "h" to shoot on the left and rightside from the original shootplace
        // Implement aircraftCarrier ability

    }
}