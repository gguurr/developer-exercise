package assignment.events;

import assignment.api.ItemType;
import net.gameslabs.api.Player;

public class RemoveItemEvent extends ItemEvent {
    public RemoveItemEvent(Player player, ItemType item, int amount) {
        super(player, item);
        this.amount = amount;
    }
}
