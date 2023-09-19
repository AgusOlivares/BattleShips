package battleShip.entities.Ships;

import battleShip.Interface.SpecialShipInterface;
import battleShip.entities.*;
import org.jetbrains.annotations.NotNull;
/**
 * Una clase encargada de crear un Submarino ('Submarine') Subclase de 'Ship'
 * @see Ship
 * @author Agustin Olivares
 * @version 1.2, 08/09/2023
 */
public class Submarine extends Ship implements SpecialShipInterface {

    public Submarine() {
        super(2);
    }

    /**
     * Implementacion polimorfica del metodo de 'Ship', utiliza la habilidad especial del Submarino.
     * Coloca un señuelo en la posicion indicada, cuando el jugador contrario dispare en esta area figurara como que se ha disparado en un barco sin afectar la lista de barcos del otro jugador.
     * @see battleShip.entities.Decoy
     * @see Ship
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario (En este caso no es necesario que sea completado).
     * @return
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy) {


        Cell CellP1 = player.getMap().getCell(pos);
        Game auxGame = new Game();
        boolean successfullDecoy = false;

        while (!successfullDecoy){

            auxGame.showShipMap(player);
            System.out.println("Seleccione la posicion del señuelo");

            if (CellP1.getElement() != null || CellP1.getWasShot()){
                System.out.println("posicion invalida");
                System.out.println("");
            } else {
                Decoy Senuelo = new Decoy(CellP1);
                CellP1.setElement(Senuelo);
                System.out.println("El señuelo se colocó correctamente!");
                System.out.println("");
                successfullDecoy = true;
            }
        }
        return true;
    }
    @Override
    public void showAbility() {

        System.out.println("Señuelo: ");
        System.out.println("La habilidad especial del Submarino coloca un señuelo en tu mapa.");
        System.out.println("Cuando el Enemigo impacte al señuelo con un disparo a este se le informara que golpeo uno de tus barcos");
        System.out.print(" pero éste habrá caido en la trampa!");

    }
    @Override
    public void showExample(){

        int charLegendCnt = 64;

        // Encabezado de números
        System.out.print("   ");
        for (int i = 1; i <= 3; i++) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");

        // Llenado matriz y leyenda de filas
        for (int i = 0; i < 3; i++) {

            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);

            // Condicionales para los caracteres
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.print("| 0 ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }

        System.out.println("Mapa propio con el señuelo plantado en B2");
        System.out.println("");

        charLegendCnt = 64;

        // Encabezado de números
        System.out.print("   ");
        for (int i = 1; i <= 3; i++) {
            System.out.printf("| %d ", i);
        }
        System.out.println("|");

        // Llenado matriz y leyenda de filas
        for (int i = 0; i < 3; i++) {

            charLegendCnt += 1;
            char charLegend = (char) charLegendCnt;
            System.out.printf(" %s ", charLegend);

            // Condicionales para los caracteres
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.print("| X ");
                } else {

                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("Un barco enemigo fue golpeado");
        System.out.println("Mapa enemigo cuando acierte un disparo en el señuelo");
        System.out.println("");
    }
}