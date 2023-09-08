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
    public Boolean startGame() {
        initPlayers(); //Asks players names
        askTurnAmount();
        return true;
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
        boolean isGameInProgress = this.startGame();
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

        do {
            for (Player actualPlayer : this.getPlayers()) {
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
        } else {
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
                if (map.getCell(i, j).getElement() != null) {
                    System.out.print("| � ");
                } else {
                    System.out.print("|   ");
                }

            }
            System.out.println(" |");
        }
        System.out.println();
    }
}
