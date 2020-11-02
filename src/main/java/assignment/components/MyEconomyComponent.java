package assignment.components;

import assignment.events.*;
import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;

import java.util.HashMap;
import java.util.Map;

public class MyEconomyComponent extends Component {

    private Map<Player, Integer> persistence;

    public final static int BASE_XP_PER_BUY = 5;
    public final static int STARTING_COINS = 0;

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
            send(new GiveXpEvent(event.getPlayer(), Skill.ECONOMY, BASE_XP_PER_BUY * event.getAmount()));
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
        event.setPrice(Math.max(event.getItem().getMinValue(), event.getItem().getBaseValue() -
                levelEvent.getLevel() * event.getItem().getPriceRedaction() + event.getItem().getMinValue()));
    }

    private void onGiveCoins(GiveCoinsEvent event) {
        persistence.put(event.getPlayer(),getCoins(event.getPlayer()) + event.getAmount());
    }

    private void onGetCoins(GetCoinsEvent event) {
        event.setAmount(getCoins(event.getPlayer()));
    }

    private int getCoins(Player player) {
        return persistence.computeIfAbsent(player, p -> STARTING_COINS);
    }

    @Override
    public void onUnload() {

    }
}
