package battleShip.entities;

public class Cruiser extends Special_Ship{
    public Cruiser(String[] occupiedCells, String[] shotedCells, String[] ability) {
        super(4, occupiedCells, ability);
    }

    @Override
    public void useAbility() {
        // Implement Cruiser ability
    }
}
