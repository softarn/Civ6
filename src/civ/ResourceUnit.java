package civ;

public class ResourceUnit{
    private ResourceType type;
    private int amount;
    
    ResourceUnit(ResourceType type, int amount) {
        this.type=type;
        this.amount=amount;
    }
    
    public ResourceType getResourceType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
    
    public String toString(){
        return type.getName() +" " + amount;
    }
    
}