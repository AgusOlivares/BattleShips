package battleShip.entities.Ships;

import battleShip.Interface.SpecialShipInterface;
import battleShip.entities.MapElements.*;
import battleShip.entities.*;
import battleShip.entities.MapElements.Decoy;
import org.jetbrains.annotations.NotNull;
/**
 * Una clase encargada de crear un Submarino ('Submarine') Subclase de 'Ship'
 * @see Ship
 * @author Agustin Olivares
 * @version 1.2, 08/09/2023
 */
public class Submarine extends Ship implements SpecialShipInterface {

    /**
     * Construye un objeto Cruiser, inicializando el atributo 'length' en 4.
     */
    public Submarine() {
        super(2);
    }

    /**
     * Implementacion polimorfica del metodo de 'Ship', utiliza la habilidad especial del Submarino.
     * Coloca un señuelo en la posicion indicada, cuando el jugador contrario dispare en esta area figurara como que se ha disparado en un barco sin afectar la lista de barcos del otro jugador.
     * @see battleShip.entities.MapElements.Decoy
     * @see Ship
     * @param player El jugador que invoca el metodo.
     * @param pos La posicion objetivo.
     * @param Enemy El jugador contrario (En este caso no es necesario que sea completado).
     * @return true si se pudo colocar correctamente el señuelo, false en caso contrario
     */
    @Override
    public Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy) {
        Cell CellP1 = player.getMap().getCell(pos);

        if (CellP1.getElement() instanceof Water && !(CellP1.getWasShot())){
            Decoy decoy = new Decoy(CellP1);
            CellP1.setElement(decoy);
            System.out.println("El señuelo se colocó correctamente");
        } else {
            System.out.println("posicion invalida");
            return false;
        }

        return true;
    }

    /**
     * Metodo encargado de explicar al jugador la habilidad del barco
     */
    @Override
    public void showAbility() {

        System.out.println("Señuelo: ");
        System.out.println("La habilidad especial del Submarino coloca un señuelo en tu mapa.");
        System.out.println("Cuando el Enemigo impacte al señuelo con un disparo a este se le" +
                           "\ninformará que golpeó uno de tus barcos, ¡pero este habrá caído en la trampa!");
        System.out.println("");
    }

    /**
     * Imprime por consola una matriz que muestra un ejemplo de la habilidad del barco.
     */
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