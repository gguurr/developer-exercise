package assignment.components;

import assignment.api.ItemType;
import assignment.events.GiveItemEvent;
import assignment.events.MineEvent;
import net.gameslabs.api.Component;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Assignment;
import net.gameslabs.model.Skill;

public class MyMiningComponent extends Component {


    @Override
    public void onLoad() {
        registerEvent(MineEvent.class, this::onMine);
    }

    private void onMine(MineEvent event) {
        GetPlayerLevel level = new GetPlayerLevel(event.getPlayer(), Skill.MINING);
        send(level);
        if (level.getLevel() > 5){
            send(new GiveXpEvent(event.getPlayer(),Skill.MINING,15));
            send(new GiveItemEvent(event.getPlayer(), ItemType.COAL,1));
        }else {
            event.setCancelled(true);
        }
    }

    @Override
    public void onUnload() {
        // Do nothing
    }

}
