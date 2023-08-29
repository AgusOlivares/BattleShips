package battleShip.entities.Ships;

import battleShip.entities.Ship;

public abstract class SpecialShip extends Ship {
    protected String ability;
    protected final int abilityCost;

    //Constructor
    public SpecialShip(int length,  String ability) {
        super(length);
        this.ability = ability;
        this.abilityCost = switch (length) {
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 7;
            case 5 -> 9;
            default -> 0; // Idea for setting ability cost
        };
    }

    // Getters
    public String getAbility() {
        return ability;
    }
    public int getAbilityCost() {
        return abilityCost;
    }

    //Setters
    public void setAbility(String ability) {
        this.ability = ability;
    }

    //Methods
    public abstract void useAbility(); // Set ability for each diferent specialShip type
}
