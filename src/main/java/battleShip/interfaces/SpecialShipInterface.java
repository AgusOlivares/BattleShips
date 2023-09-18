package battleShip.interfaces;

import battleShip.entities.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Interfaz encargada de la utilizacion de las habilidades de los distintos barcos especiales, contiene metodo para explicarle al jugador que hace la habilidad del barco seleccionado
 * @author Agustin Olivares
 * @version 1.1 18/09/23
 */
public interface SpecialShipInterface {


    public Boolean useAbility(@NotNull Player player, @NotNull String pos, Player Enemy);

    public Void showAbility();
}
