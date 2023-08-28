package battleShip.entities;

public class Warship extends Special_Ship{

    public Warship(String[] occupiedCells, String[] shotedCells, String[] ability) {
        super(3, occupiedCells, ability);
    }

    @Override
    public void useAbility() {
        // Implement Warship ability
    }
}
