package battleShip;

public class BattleShip {

    public static void main(String[] args) {
        System.out.println("Mi flota");
        System.out.println("|�|  Parte de barco intacta");
        System.out.println("|X|  Parte de barco impactada");
        int decimalValue = Integer.parseInt("10100100", 2);
        char character = (char) decimalValue;
        System.out.println("Flota enemiga");
        System.out.println("|"+ character+"|  Acierto");
        System.out.println("|o|  Agua/le chingó");
        System.out.println("Ambas flotas");
        System.out.println("| |  Agua - Sin conocer");
    }
}
