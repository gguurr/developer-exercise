package assignment.model;

public enum ItemType {
    IRON(100,2,2),
    GOLD(100,5,2),
    COPPER(300,2,2),
    COAL(150,2,3);
    int baseValue;
    int minValue;
    int priceRedaction;
    ItemType(int baseValue,int minValue,int priceRedaction){
        this.baseValue = baseValue;
        this.minValue = minValue;
        this.priceRedaction = priceRedaction;
    }

    public int getBaseValue(){
        return baseValue;
    }

    public int getMinValue(){
        return minValue;

    }

    public int getPriceRedaction(){
        return priceRedaction;
    }
}
