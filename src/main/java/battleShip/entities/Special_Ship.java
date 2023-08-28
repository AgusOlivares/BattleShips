package battleShip.entities;

public abstract class Special_Ship extends Ship{

    protected String[] ability;

    protected final int abilityCost;

    // misma duda de privacidad constructor, getter/setter
    public Special_Ship(int lenght, String[] occupiedCells, String[] ability) {

        super(lenght, occupiedCells);
        this.ability = ability;

        int abilityCostValue = 0; // Idea for setting ability cost

        switch (lenght){
            case 2:
                abilityCostValue = 3;
                break;
            case 3:
                abilityCostValue = 5;
                break;
            case 4:
                abilityCostValue = 7;
                break;
            case 5:
                abilityCostValue = 9;
        }
        this.abilityCost = abilityCostValue;
    }

    public String[] getAbility() {
        return ability;
    }

    public void setAbility(String[] ability) {
        this.ability = ability;
    }

    public int getAbilityCost() {
        return abilityCost;
    }

    public abstract void useAbility(); // Set ability for each diferent specialShip type
}
