package assignment.events;

import assignment.api.ItemType;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

public class EconomyEvent extends PlayerEvent {

    protected int amount;
    public EconomyEvent(Player player, int amount) {
        super(player);
        this.amount = amount;
    }



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
