package assignment.components;

import assignment.api.ItemType;
import assignment.events.*;
import assignment.model.Inventory;
import net.gameslabs.api.Component;
import net.gameslabs.api.Event;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;

import java.util.HashMap;
import java.util.Map;

public class MyEconomyComponent extends Component {

    private Map<Player, Integer> persistence;



    public MyEconomyComponent() {
        persistence = new HashMap<>();
    }
    @Override
    public void onLoad() {
        registerEvent(GetCoinsEvent.class, this::onGetCoins);
        registerEvent(GiveCoinsEvent.class, this::onGiveCoins);
        registerEvent(GetItemPriceEvent.class, this::onGetPrice);
        registerEvent(TakeCoinsEvent.class, this::onTakeCoins);
        registerEvent(BuyEvent.class, this::onBuy);
    }

    private void onBuy(BuyEvent event) {
        GetItemPriceEvent priceEvent = new GetItemPriceEvent(event.getItem(),event.getPlayer());
        send(priceEvent);
        int price = priceEvent.getPrice() * event.getAmount();
        TakeCoinsEvent takeEvent = new TakeCoinsEvent(event.getPlayer(),price);
        send(takeEvent);
        if(!takeEvent.isCancelled()){
            send(new GiveItemEvent(event.getPlayer(),event.getItem(),event.getAmount()));
            send(new GiveXpEvent(event.getPlayer(), Skill.ECONOMY,5 * event.getAmount()));
        }
    }

    private void onTakeCoins(TakeCoinsEvent event) {
        if(event.getAmount() > getCoins(event.getPlayer())){
            event.setCancelled(true);
        }else {
            persistence.put(event.getPlayer(),getCoins(event.getPlayer()) - event.getAmount());
        }
    }

    private void onGetPrice(GetItemPriceEvent event) {
        GetPlayerLevel levelEvent = new GetPlayerLevel(event.getPlayer(),Skill.ECONOMY);
        send(levelEvent);
        event.setPrice((int) Math.max(2, 100 - (levelEvent.getLevel() -1) * 2));
    }

    private void onGiveCoins(GiveCoinsEvent event) {
        persistence.put(event.getPlayer(),getCoins(event.getPlayer()) + event.getAmount());
    }

    private void onGetCoins(GetCoinsEvent event) {
        event.setAmount(getCoins(event.getPlayer()));
    }

    private int getCoins(Player player) {
        return persistence.computeIfAbsent(player, p -> 0);
    }

    @Override
    public void onUnload() {

    }
}
