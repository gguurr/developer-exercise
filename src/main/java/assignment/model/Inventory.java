package assignment.model;

import assignment.api.ItemType;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<ItemType, Integer> items;
    public Inventory(){
        items = new HashMap<>();
    }
    public void setItemAmount(ItemType item, int amount) {
        items.put(item, amount);
    }

    public int getItemAmount(ItemType item) {
        return items.getOrDefault(item, 0);
    }

    public void addItem(ItemType item, int amount) {
        setItemAmount(item, getItemAmount(item) + amount);
    }
}
