package assignment.events;

import assignment.api.ItemType;
import assignment.api.Ores;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

public class MineEvent extends PlayerEvent {

    private Ores ore;
    public MineEvent(Player player, Ores ore) {
        super(player);
        this.ore = ore;
    }

    public Ores getOre() {
        return ore;
    }

    public void setOre(Ores ore) {
        this.ore = ore;
    }
}
