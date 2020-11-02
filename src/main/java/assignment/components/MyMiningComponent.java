package assignment.components;

import assignment.events.GiveItemEvent;
import assignment.events.MineEvent;
import net.gameslabs.api.Component;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;

public class MyMiningComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(MineEvent.class, this::onMine);
    }

    private void onMine(MineEvent event) {
        GetPlayerLevel levelEvent = new GetPlayerLevel(event.getPlayer(), Skill.MINING);
        send(levelEvent);

        if (levelEvent.getLevel() > event.getOre().getMinLevel()){
            send(new GiveXpEvent(event.getPlayer(),Skill.MINING,event.getOre().getXp()));
            send(new GiveItemEvent(event.getPlayer(), event.getOre().getDrop(),event.getOre().getDropAmount()));
        }else {
            event.setCancelled(true);
        }
    }

    @Override
    public void onUnload() {
        // Do nothing
    }

}
