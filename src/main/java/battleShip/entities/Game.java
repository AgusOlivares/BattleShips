package battleShip.entities;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import javax.swing.text.Position;

public class Game {

    private int maxTurns;
    private Player[] players;

    public Game(int maxTurns, String namePlayer1, String namePlayer2) {
        this.maxTurns = maxTurns;
        Player player1 = new Player(namePlayer1);
        Player player2 = new Player(namePlayer2);
        Player[] newPlayers = {player1, player2};
        this.players = newPlayers;
    }

    public Game(int maxTurns, Player[] players) {
        this.maxTurns = maxTurns;
        this.players = players;
    }

    public Game() {
        this.maxTurns = 0;
        this.players = null;
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    //Methods
    //Creates players
    public void startGame() {
        initPlayers(); //Asks players names
        askTurnAmount();
    }

    //Asks player names and returns an arraylist of players
    public void initPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al juego de Batalla Naval!");
        System.out.println("Jugador 1, ingrese su nombre: ");
        String nameP1 = scanner.nextLine();
        System.out.println("Jugador 2, ingrese su nombre: ");
        String nameP2 = scanner.nextLine();
        Player player1 = new Player(nameP1);
        Player player2 = new Player(nameP2);
        Player[] newPlayers = {player1, player2};
        this.players = newPlayers;
    }

    //Ask turn amount and checks if it's correct
    public void askTurnAmount() {
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
        this.maxTurns = turnAmount;
    }

    public void showMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        Player enemy = getOppossitePlayer(player);
        String option;
        boolean shot = false;
        do {
            System.out.println(player.getName() + ", por favor ingrese alguna de las opciones");
            System.out.println("1: ver mi mapa");
            System.out.println("2: ver mapa enemigo");
            System.out.println("3: disparar");
            System.out.println("4: terminar turno");
            option = scanner.nextLine();
            if(option.equals("1")){
                showShipMap(player);
                waitXSeconds(5);
            }else if (option.equals("2")) {
                showShotsMap(player);
                waitXSeconds(5);
            }else if(option.equals("3")){
                if(shot){
                    System.out.println("Ya has disparado, espera a tu siguiente turno para realizar otro disparo");
                } else{
                    String position = askCoordinates();
                    shot = player.shoot(position, enemy);
                }
            }else if(option.equals("4")){
                if(shot){
                    System.out.println("Turno finalizado");
                }else{
                    System.out.println("No has realizado el tiro, por favor ataca al enemigo antes de terminar tu turno");
                    option = "";
                }
            }else{
                System.out.println("Opción incorrecta, intente de nuevo");
            }

        } while (!option.equals("4"));

    }

    //1: allows players to place their ships
    //2: Once the ships are placed, they start playing by choosing options in the menu
    public void playGame() {
        this.startGame();
        String winner = "";
        
        for (Player actualPlayer : this.getPlayers()) {
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
        
        for (int i = 1; i <= maxTurns; i++) {
            for (Player actualPlayer : this.getPlayers()) {
                showMenu(actualPlayer);
                if(hasPlayerWon(actualPlayer)){
                    if(actualPlayer == this.getPlayers()[0]){
                        winner = "0";
                    }else{
                        winner = "1";
                    }
                    String winnersName = actualPlayer.getName();
                    if(sunkenShipCounter(actualPlayer) == actualPlayer.getShips().size()-1){
                        actualPlayer = getOppossitePlayer(actualPlayer);
                        System.out.println(actualPlayer.getName() + ", " + winnersName + " ha hundido todos tus barcos, tienes una última oportunidad para empatar");
                        showMenu(actualPlayer);
                        if(hasPlayerWon(actualPlayer)){
                            winner = "01";
                        } 
                    }else{
                        System.out.println(winnersName + ", has ganado la partida, ¡felicitaciones!");
                    }
                    break;
                }
            }
        }
        if(winner.equals("01") || winner.equals("")){
            System.out.println("¡Empate! La partida ha finalizado");
        }
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
        } else {
            finalPosition = initialPosition;
        }
        String[] coordinates = new String[]{initialPosition, finalPosition};
        coordinates = checkReverse(coordinates);
        
        return player.placeShip(coordinates[0], coordinates[1], ship);
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
            position = scanner.nextLine().toUpperCase();
            if (position.length() < 1 || position.length() > 3) {
                isCorrectPosition = false;
                System.out.println("Incorrecto, por favor intente de nuevo");
                waitXSeconds(1);
                continue;
            }
            switch (position.substring(0, 1)) {
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

    private Player getOppossitePlayer(Player currentPlayer) {
        Player player1 = this.players[0];
        Player player2 = this.players[1];

        if (player1.equals(currentPlayer)) {
            return player2;
        } else if (player2.equals(currentPlayer)) {
            return player1;
        } else {
            throw new IllegalArgumentException("The specified player is not in the ArrayList.");
        }
    }

    // Prints Matrix to console with the annotation of the player shots
    public void showShotsMap(Player player) {
        Player oppPlayer = getOppossitePlayer(player);
        // Top Number Legend
        Map map = player.getMap();
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < map.getBoard().size() + 1; i++) {
            if (i == 0) {
                System.out.print("   ");
            } else {
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < map.getBoard().size(); i++) {
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < map.getBoard().get(0).size(); j++) {
                // Indicators
                if (map.isCellShot(player.getMap().getCell(i, j))) {
                    if (oppPlayer.getMap().getCell(i, j).getElement() instanceof Ship) {

                        System.out.printf("| X ");
                    } else {
                        System.out.printf("| O ");
                    }

                } else {
                    System.out.printf("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }

    // Prints Matrix to console with the player boats and if their are hit
    public void showShipMap(Player player) {

        Map map = player.getMap();
        // Top Number Legend
        int charLegendCnt = 64;
        System.out.println();
        for (int i = 0; i < map.getBoard().size() + 1; i++) {
            if (i == 0) {
                System.out.print("   ");
            } else {
                System.out.printf("| %s ", i);
            }

        }
        System.out.println("|");
        for (int i = 0; i < map.getBoard().size(); i++) {
            // Left Character Legend
            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);
            for (int j = 0; j < map.getBoard().get(0).size(); j++) {
                // Indicators
                Cell cell = map.getCell(i, j);
                if (cell.getElement() != null) {
                    if(cell.getWasShot()){
                        System.out.print("| X ");
                    }else{
                        System.out.print("| � ");
                    }
                    
                } else {
                    System.out.print("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }
    
    public boolean hasPlayerWon(Player player){
        Player enemy = getOppossitePlayer(player);
        int sunkenShips = sunkenShipCounter(enemy);
        return sunkenShips == enemy.getShips().size();
    }
    
    public int sunkenShipCounter(Player player){
        Iterator<Ship> ships = player.getShips().iterator();
        int sunkenShips = 0;
        while(ships.hasNext()) {
            Ship ship = ships.next();
            if(ship.getSunken()){
                sunkenShips++;
            }
        }
        return sunkenShips;
    }
    
    public String[] checkReverse(String[] coordinates){
        boolean reversedString = false;
        if(coordinates[0].substring(0, 1).equals(coordinates[1].substring(0, 1))){
            if(Integer.parseInt(coordinates[0].substring(1)) > Integer.parseInt(coordinates[1].substring(1))){
                reversedString = true;
            }
        }else if(coordinates[0].substring( 1).equals(coordinates[1].substring( 1))){
            int result = coordinates[0].substring(0, 1).compareTo(coordinates[1].substring(0, 1));
            if(result > 0){
                reversedString = true;
            }
        }
        if(reversedString){
            String auxPosition = coordinates[0];
            coordinates[0] = coordinates[1];
            coordinates[1] = auxPosition;
        }
        return coordinates;
    }
}
