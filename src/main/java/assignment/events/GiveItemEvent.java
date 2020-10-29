package assignment.events;

import assignment.api.ItemType;
import net.gameslabs.api.Player;

public class GiveItemEvent extends ItemEvent {

    public GiveItemEvent(Player player, ItemType item, int amount) {
        super(player, item);
        this.amount = amount;
    }

}
