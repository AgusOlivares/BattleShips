package battleShip.entities;

public class Submarine extends Special_Ship{

    public Submarine(String[] occupiedCells, String[] shotedCells, String[] ability) { // consultar chicos por como llenar "ability"
        super(2, occupiedCells, ability);
    }

    @Override
    public void useAbility() {
        // Implement submarine ability
        }
}
