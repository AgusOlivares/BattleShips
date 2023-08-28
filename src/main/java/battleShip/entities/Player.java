package battleShip.entities;

public class Player {
    private String name;
    private Boat[] boats;
    private Map map;
    private int charges;

    //Constructor
    public Player(String name){
        this.name = name;
        // to do: inicializar los 5 barcos junto al jugador
        this.boats = null;
        this.map = new Map();
        this.charges = 0;
    }

    //Getters
    public String getName() {
        return name;
    }

    public Boat[] getBoats() {
        return boats;
    }

    public Map getMap() {
        return map;
    }
    public int getCharges() {
        return charges;
    }

    //Setters
    public void setBoats(Boat[] boats) {
        this.boats = boats;
    }
    public void setCharges(int charges) {
        this.charges = charges;
    }

    //Methods
    public boolean placeBoat(){
        return true;
    }
    public String shoot(String pos, Player p2){ // String like "A6" expected
        int x = pos.substring(0,1).charAt(0) - 'A';
        int y = Integer.parseInt(pos.substring(1)) - 1;
        Cell cellP1 = this.map.getBoard()[x][y];
        Cell cellP2 = p2.getMap().getBoard()[x][y];
        if (!cellP2.getWasShot()){
            cellP2.setWasShot(true);
            if (cellP2.getBoat() != null){
                cellP1.setElement("X");
                return "Hit";
            }
            else{
                cellP1.setElement("O");
                return "Water";
            }

        }
        return "Position already shot, pick another position: ";
    }

    public String shoot(String pos, Boat boat, Player p2){
        return "x";
    }

    public void showMap(){
        // Top Number Legend
        int charLegendCnt = 64;
        for (int i = 0; i < this.map.getBoard().length + 1; i++){
            if (i == 0){
                System.out.printf("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < this.map.getBoard().length; i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < this.map.getBoard()[0].length; j++){
                // Indicators
                System.out.printf("| %s ", map.getBoard()[i][j].getElement());
            }
            System.out.println(" |");
        }
        System.out.println();
    }

    public void showMapBoatPlacement(){
        // Top Number Legend
        int charLegendCnt = 64;
        for (int i = 0; i < this.map.getBoard().length + 1; i++){
            if (i == 0){
                System.out.printf("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < this.map.getBoard().length; i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < this.map.getBoard()[0].length; j++){
                // Indicators
                if (map.getBoard()[i][j].getBoat() != null ){
                    System.out.printf("| � ");
                }
                else {
                    System.out.printf("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }
}
