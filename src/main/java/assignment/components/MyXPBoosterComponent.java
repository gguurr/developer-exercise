package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;

public class MyXPBoosterComponent extends Component {

    private int mod;
    private Skill skill;
    public MyXPBoosterComponent(int mod, Skill skill){
        this.mod = mod;
        this.skill = skill;
    }

    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) {
        if (event.getSkill() == skill){
            event.setXp(event.getXp() * mod);
        }

    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
