package battleShip.entities;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import javax.swing.text.Position;

public class Game {

    private int turnCount;
    private int maxTurns;
    private ArrayList<Player> players;

    public Game() {
        this.turnCount = 0;
        this.maxTurns = 100;
    }

    public Game(int maxTurns, ArrayList<Player> players) {
        this.turnCount = 0;
        this.maxTurns = maxTurns;
        this.players = players;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void nextRound() {
        setTurnCount(getTurnCount() + 1);
    }

    //Methods
    //Creates players
    public Game startGame() {
        ArrayList<Player> newPlayers = initPlayers(); //Asks players names
        int turnAmount = askTurnAmount();  
        Game game = new Game(turnAmount, newPlayers);
        return game;
    }
    
    //Asks player names and returns an arraylist of players
    public ArrayList<Player> initPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de Batalla Naval!");
        System.out.println("Jugador 1, ingrese su nombre: ");
        String nameP1 = scanner.nextLine();
        System.out.println("Jugador 2, ingrese su nombre: ");
        String nameP2 = scanner.nextLine();
        Player player1 = new Player(nameP1);
        Player player2 = new Player(nameP2);
        ArrayList<Player> newPlayers = new ArrayList<>(2);
        newPlayers.add(0, player1);
        newPlayers.add(1, player2);
        return newPlayers;
    }

    //Ask turn amount and checks if it's correct
    public int askTurnAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Desean tener un límite de turnos? S/N");
        String option;
        do {
            option = scanner.nextLine().toUpperCase();
            if (!option.equals("S") && !option.equals("N")) {
                System.out.println("Opción incorrecta, intente de nuevo");
            }
        } while (!option.equals("S") && !option.equals("N"));
        int turnAmount;
        if (option.equals("S")) {
            System.out.println("¿Cuántos turnos desean jugar como máximo? Recuerda que el límite es 100");
            do {
                turnAmount = scanner.nextInt();
                if (turnAmount > 100 || turnAmount < 0) {
                    System.out.println("Opción incorrecta, intente de nuevo");
                }
            } while (turnAmount > 100 || turnAmount < 0);
        } else {
            turnAmount = 100;
        }
        return turnAmount;
    }

    public int showMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor ingrese alguna de las opciones");
        System.out.println("1: ver mi mapa");
        System.out.println("2: ver mapa enemigo");
        System.out.println("3: disparar");
        int option;
        do {
            option = scanner.nextInt();
        } while (option < 1 || option > 3);
        switch (option) {
            case 1:
                break;
            default:
                throw new AssertionError();
        }
        return 1;
    }

    //1: allows players to place their ships
    //2: Once the ships are placed, they start playing by choosing options in the menu
    public void playGame() {
        Game game = startGame();
        boolean isGameInProgress = true;
        Iterator<Player> playerSet = game.getPlayers().iterator();
        while (playerSet.hasNext()) {
            Player actualPlayer = playerSet.next();
            Iterator<Ship> ships = actualPlayer.getShips().iterator();
            while (ships.hasNext()) {
                Ship ship = ships.next();
                Boolean isBoatPlaced = false;
                do {
                    this.showShipMap(actualPlayer);
                    waitXSeconds(3);
                    isBoatPlaced = askShipPosition(actualPlayer, ship);
                } while (!isBoatPlaced);
                System.out.println("El barco " + getShipName(ship).toUpperCase() + " fue colocado exitosamente");
                waitXSeconds(2);
            }
            System.out.println(actualPlayer.getName() + ", has colocado todos tus barcos exitosamente");
        }
        
        do {
            while (playerSet.hasNext()) {
                Player actualPlayer = playerSet.next();
                showMenu(actualPlayer);
            }

        } while (isGameInProgress);
    }

    //Requests the start and end position of the ship 
    //and verifies that both are correct and that the ship can be placed where desired
    public Boolean askShipPosition(Player player, Ship ship) {
        System.out.println(player.getName() + ", a continuación ingresarás la posición inicial del barco " + getShipName(ship).toUpperCase());
        System.out.println("El barco " + getShipName(ship) + " sólo puede ocupar " + ship.getLength() + " casillas");
        waitXSeconds(2);
        String initialPosition = askCoordinates();
        String finalPosition;
        if (ship.getLength() > 1) {
            System.out.println("Ahora ingresarás la posición final del barco " + getShipName(ship).toUpperCase());
            waitXSeconds(2);
            finalPosition = askCoordinates();
        }else{
            finalPosition = initialPosition;
        }
        return player.placeShip(initialPosition, finalPosition, ship);
    }

    //Ponemos a "Dormir" el programa durante los ms que queremos
    public void waitXSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    //Ask coordinates to the player and checks that they are correct
    public String askCoordinates() {
        Scanner scanner = new Scanner(System.in);
        boolean isCorrectPosition;
        String position;
        do {
            System.out.println("Ingrese la coordenada en el mapa");
            System.out.println("Recuerde que las coordenadas incluyen una letra (A-J) y un número (1-10), como por ejemplo A7");
            position = scanner.nextLine();
            if (position.length() < 1 || position.length() > 3) {
                isCorrectPosition = false;
                System.out.println("Incorrecto, por favor intente de nuevo");
                waitXSeconds(1);
                continue;
            }
            switch (position.substring(0, 1).toUpperCase()) {
                case "A", "B", "C", "D", "E", "F", "G", "H", "I", "J":
                    isCorrectPosition = true;
                    break;
                default:
                    System.out.println("Incorrecto, por favor intente de nuevo");
                    isCorrectPosition = false;
                    waitXSeconds(1);
                    continue;
            }
            switch (position.substring(1)) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10":
                    isCorrectPosition = true;
                    break;
                default:
                    isCorrectPosition = false;
                    System.out.println("Incorrecto, por favor intente de nuevo");
                    waitXSeconds(1);
                    break;
            }
        } while (!isCorrectPosition);
        return position;
    }

    //Returns the name of the ship according to their length
    public String getShipName(Ship ship) {
        switch (ship.getLength()) {
            case 1:
                return "lancha";
            case 2:
                return "crucero";
            case 3:
                return "submarino";
            case 4:
                return "buque";
            case 5:
                return "portaaviones";
            default:
                return null;
        }
    }

    // Prints Matrix to console with the annotation of the player shots
    public void showShotsMap(Player player){
        // Top Number Legend
        Map map = player.getMap();
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < map.getBoard().size() + 1; i++){
            if (i == 0){
                System.out.print("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < map.getBoard().size(); i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < map.getBoard().get(0).size(); j++){
                // Indicators
                System.out.printf("| %s ", map.getCell(i,j).getElement());
            }
            System.out.println(" |");
        }
        System.out.println();
    }


    // Prints Matrix to console with the player boats and if their are hit
    public void showShipMap(Player player){

        Map map = player.getMap();
        // Top Number Legend
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < map.getBoard().size() + 1; i++){
            if (i == 0){
                System.out.print("   ");
            }
            else{
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < map.getBoard().size(); i++){
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < map.getBoard().get(0).size(); j++){
                // Indicators
                if (map.getCell(i,j).getShip() != null ){
                    if (!map.getCell(i, j).getShip().getSunken()){
                        System.out.print("| � ");
                    }else if(map.getCell(i, j).getShip().getSunken()){
                        System.out.print("| X ");
                    }
                }
                else {
                    System.out.print("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }
}


