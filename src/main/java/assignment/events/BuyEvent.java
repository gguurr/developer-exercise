package assignment.events;

import assignment.api.ItemType;
import net.gameslabs.api.Player;

public class BuyEvent extends EconomyEvent {
    private ItemType item;
    public BuyEvent(Player player, ItemType item, int amount) {
        super(player,amount);
        this.item = item;
    }

    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }
}
