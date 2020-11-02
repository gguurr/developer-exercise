package net.gameslabs.model;

import assignment.model.ItemType;
import assignment.model.Ores;
import assignment.events.*;
import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GetXPForLevelEvent;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.implem.PlayerImplem;

import java.util.Arrays;

public class Assignment {

    protected final ComponentRegistry registry;
    private final Player mainPlayer;

    public Assignment(Component ... myComponentsToAdd) {
        registry = new ComponentRegistry();
        Arrays.asList(myComponentsToAdd).forEach(registry::registerComponent);
        registry.registerComponent(new ChartComponent());
        registry.load();
        mainPlayer = PlayerImplem.newPlayer("MyPlayer");
    }

    public final void run() {
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.CONSTRUCTION, 25));
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.EXPLORATION, 25));

        registry.sendEvent(new GiveItemEvent(mainPlayer, ItemType.COPPER,5));
        registry.sendEvent(new RemoveItemEvent(mainPlayer, ItemType.COPPER,2));
        registry.sendEvent(new MineEvent(mainPlayer, Ores.COAL));

        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.MINING, getRequiredXPForLevel(5)));

        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(mainPlayer, Skill.MINING);
        registry.sendEvent(getPlayerLevel);
        registry.sendEvent(new MineEvent(mainPlayer, Ores.COAL));
        registry.sendEvent(new BuyEvent(mainPlayer,ItemType.GOLD,10));
        registry.sendEvent(new GiveCoinsEvent(mainPlayer,2060));
        registry.sendEvent(new BuyEvent(mainPlayer,ItemType.GOLD,10));
        registry.sendEvent(new BuyEvent(mainPlayer,ItemType.GOLD,10));

        runChecks();
        registry.unload();
    }

    private void runChecks() {
        if (getLevel(Skill.EXPLORATION) != 1) throw new AssignmentFailed("Exploration XP should be set to level 1");
        if (getLevel(Skill.CONSTRUCTION) != 2) throw new AssignmentFailed("Construction XP should be set to level 2");
        if (getItem(ItemType.COPPER) != 3) throw new AssignmentFailed("Copper should be set to 3");
        if (getItem(ItemType.COAL) != 2) throw new AssignmentFailed("Coal should be set to 1");
        if (getItem(ItemType.GOLD) != 20) throw new AssignmentFailed("Gold should be set to 20");
        if (getCoins() != 20) throw new AssignmentFailed("Coins should be set to 20");

    }

    private  int getRequiredXPForLevel(int level){

        GetXPForLevelEvent getXpLevel = new GetXPForLevelEvent(level);
        registry.sendEvent(getXpLevel);
        return getXpLevel.getXp();
    }

    private int getLevel(Skill skill) {
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(mainPlayer, skill);
        registry.sendEvent(getPlayerLevel);
        return getPlayerLevel.getLevel();
    }

    private int getItem(ItemType item) {
        GetItemEvent getItemEvent = new GetItemEvent(mainPlayer, item);
        registry.sendEvent(getItemEvent);
        return getItemEvent.getAmount();
    }


    private int getCoins() {
        GetCoinsEvent getItemEvent = new GetCoinsEvent(mainPlayer);
        registry.sendEvent(getItemEvent);
        return getItemEvent.getAmount();
    }

    public static void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
