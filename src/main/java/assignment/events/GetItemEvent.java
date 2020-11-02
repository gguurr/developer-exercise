package assignment.events;

import assignment.model.ItemType;
import net.gameslabs.api.Player;

public class GetItemEvent extends ItemEvent {
    public GetItemEvent(Player player, ItemType item) {
        super(player, item);
    }
}
