package assignment.events;

import net.gameslabs.api.Player;

public class GiveCoinsEvent extends EconomyEvent {
    public GiveCoinsEvent(Player player, int amount) {
        super(player,amount);
    }
}
