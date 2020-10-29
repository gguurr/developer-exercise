package assignment.events;

import net.gameslabs.api.Player;

public class GetCoinsEvent extends EconomyEvent {
    public GetCoinsEvent(Player player) {
        super(player,0);
    }
}
