package assignment.events;

import assignment.model.ItemType;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

public class ItemEvent extends PlayerEvent {

    private ItemType item;
    protected int amount;
    public ItemEvent(Player player, ItemType item) {
        super(player);
        this.item = item;
        this.amount = 0;
    }


    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
