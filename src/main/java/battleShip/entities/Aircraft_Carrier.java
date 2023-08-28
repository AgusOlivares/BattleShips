package battleShip.entities;

public class Aircraft_Carrier extends Special_Ship{
    public Aircraft_Carrier(String[] occupiedCells, String[] shotedCells, String[] ability) {
        super(5, occupiedCells, ability);
    }

    @Override
    public void useAbility() {
        // Implement aircraftCarrier ability
    }
}
