package assignment;

import assignment.components.MyEconomyComponent;
import assignment.components.MyInventoryComponent;
import assignment.components.MyMiningComponent;
import assignment.components.MyXPBoosterComponent;
import net.gameslabs.model.Assignment;
import net.gameslabs.model.Skill;

public class Main {

    public static void main(String[] args) {
        new Assignment(
            new MyXPBoosterComponent(2, Skill.CONSTRUCTION),
                new MyInventoryComponent(),
                new MyMiningComponent(),
                new MyEconomyComponent()
        ).run();
    }
}
