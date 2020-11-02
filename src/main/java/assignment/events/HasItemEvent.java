package assignment.events;

import assignment.model.ItemType;
import net.gameslabs.api.Player;

public class HasItemEvent extends ItemEvent {
    private boolean haveAny;

    public HasItemEvent(Player player, ItemType item) {
        super(player, item);
        haveAny = false;
    }

    public boolean isHaveAny() {
        return haveAny;
    }

    public void setHaveAny(boolean haveAny) {
        this.haveAny = haveAny;
    }
}
