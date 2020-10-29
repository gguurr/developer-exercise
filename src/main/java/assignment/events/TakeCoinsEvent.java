package assignment.events;

import net.gameslabs.api.Player;

public class TakeCoinsEvent extends EconomyEvent {
    public TakeCoinsEvent(Player player, int amount) {
        super(player,amount);
    }
}
