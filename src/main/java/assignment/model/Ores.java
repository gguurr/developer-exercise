package assignment.model;

public enum Ores {
    COAL(15,ItemType.COAL,5,2),
    COPPER(10,ItemType.COPPER,2,1),
    IRON(25,ItemType.IRON,10,1),
    GOLD(15,ItemType.GOLD,5,1);
    int xp;
    ItemType drop;
    int minLevel;
    int dropAmount;
     Ores(int xp, ItemType drop, int minLevel,int dropAmount){
        this.xp = xp;
        this.drop = drop;
        this.minLevel = minLevel;
        this.dropAmount = dropAmount;
     }

    public int getXp(){
     return xp;
    }

    public int getMinLevel(){
        return minLevel;
    }

    public int getDropAmount(){
         return dropAmount;
    }

    public ItemType getDrop(){
    return drop;
    }
}
