package assignment.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    public final static int EMPTY_ITEM = 0;
    private Map<ItemType, Integer> items;
    public Inventory(){
        items = new HashMap<>();
    }
    public void setItemAmount(ItemType item, int amount) {
        items.put(item, amount);
    }

    public int getItemAmount(ItemType item) {
        return items.getOrDefault(item, EMPTY_ITEM);
    }

    public void addItem(ItemType item, int amount) {
        int finalValue = getItemAmount(item) + amount;
        if(finalValue < EMPTY_ITEM){
            finalValue = EMPTY_ITEM;
        }
        setItemAmount(item, getItemAmount(item) + finalValue);
    }
}
