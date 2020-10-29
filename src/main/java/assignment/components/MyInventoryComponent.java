package assignment.components;

import assignment.api.ItemType;
import assignment.events.GetItemEvent;
import assignment.events.GiveItemEvent;
import assignment.events.RemoveItemEvent;
import assignment.model.Inventory;
import net.gameslabs.api.Component;
import net.gameslabs.api.Event;
import net.gameslabs.api.Player;
import net.gameslabs.model.PlayerStats;

import java.util.HashMap;
import java.util.Map;

public class MyInventoryComponent extends Component {

    private Map<Player, Inventory> persistence;

    public MyInventoryComponent() {
        persistence = new HashMap<>();
    }
    @Override
    public void onLoad() {
        registerEvent(GiveItemEvent.class, this::onGiveItem);
        registerEvent(GetItemEvent.class, this::onGetItem);
        registerEvent(RemoveItemEvent.class, this::onRemoveItem);
    }

    private void onRemoveItem(RemoveItemEvent event) {
        getInventory(event.getPlayer()).addItem(event.getItem(), -event.getAmount());
    }

    private void onGetItem(GetItemEvent event) {
        event.setAmount(getInventory(event.getPlayer()).getItemAmount(event.getItem()));
    }

    private void onGiveItem(GiveItemEvent event){
        getInventory(event.getPlayer()).addItem(event.getItem(), event.getAmount());
    }
    private Inventory getInventory(Player player) {
        return persistence.computeIfAbsent(player, p -> new Inventory());
    }

    @Override
    public void onUnload() {

    }
}
