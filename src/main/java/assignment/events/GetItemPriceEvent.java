package assignment.events;

import assignment.api.ItemType;
import net.gameslabs.api.Player;

public class GetItemPriceEvent extends ItemEvent {

    private int price;
    public GetItemPriceEvent(ItemType item, Player player) {
        super(player, item);
        price = 0;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
