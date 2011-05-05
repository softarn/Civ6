package civ;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PhysicalUnit implements Comparable<PhysicalUnit>{
    private static int count = 0;
    private int idNumber;
    private int manPower;
    private int currentMovementPoint;
    private PhysicalUnitType type;
    private PhysicalUnitView view;
    private Player allegiance;
    private HashMap<ResourceType, Integer> inventory;
    private int currentInvSize;
    //private City city;

    @Deprecated //Unit requires allegiance now
    public PhysicalUnit(PhysicalUnitType type){ 
        System.out.println("Using deprecated constructor PhysicalUnit(PhysicalUnitType)./n" +
                "Please use PhysicalUnit(PhysicalUnitType, Player) instead.");
        this.idNumber = ++count;
        this.type = type;
        this.allegiance = null;
        reset();
        this.currentInvSize = 0;
        this.view = new PhysicalUnitView(this);
    }

    public PhysicalUnit(PhysicalUnitType type, Player allegiance){
        this.idNumber = ++count;
        this.type = type;
        this.allegiance = allegiance;
        this.inventory = new HashMap<ResourceType, Integer>();
        reset();
        this.currentInvSize = 0;
        this.manPower = type.getMaxManPower();
        this.view = new PhysicalUnitView(this);
    }

    public PhysicalUnit(PhysicalUnit other){
        this.idNumber = ++count;
        this.manPower = other.getManPower();
        this.currentMovementPoint = other.getCurrentMovementPoint();
        this.type = other.getType();
        this.inventory =  new HashMap<ResourceType, Integer>(other.inventory);
        this.currentInvSize = other.currentInvSize;
        this.allegiance = other.allegiance;
        this.view = other.getView();
    }

    public boolean isAlly(){
        if(allegiance == null){
            return false;
        }
        return allegiance.isMe();
    }

    public boolean isInCity(){
        return false;
    }

    public PhysicalUnitType getType(){
        return type;
    }

    public PhysicalUnitView getView(){
        return view;
    }

    public int getManPower(){
        return manPower;
    }

    public void setManPower(int manPower){
        this.manPower = manPower;
    }

    public int getCurrentMovementPoint(){
        return currentMovementPoint;
    }

    public int getID(){
        return idNumber;
    }

    public boolean useMovementPoints(int point){
        if(currentMovementPoint >= point){
            currentMovementPoint -= point;
            return true;
        }
        return false; // No more movementpoints
    }

    public boolean addItem(ResourceUnit item){
        if(inventory.size() > type.getInventorySize()){
            return false; // Inventory full
        }
        if(inventory.containsKey(item.getResourceType())){
            inventory.put(item.getResourceType(), item.getAmount() + inventory.get(item.getResourceType()));
            currentInvSize += item.getAmount();
        }
        else{
            inventory.put(item.getResourceType(), item.getAmount());
            currentInvSize += item.getAmount();
        }
        return true;
    }

    /**
     * Uses one item of the resource type in the inventory of the unit.
     *
     * @param type The type of resource you want to use
     *
     * @return True if the resourcetype was available and was used, otherwise false.
     */
    public boolean useItem(ResourceType type){
        if(!inventory.containsKey(type)){
            return false; // No such resource type
        }
        int amount = inventory.get(type);
        if(amount <= 0){
            return false; // No more units of this type
        }
        inventory.put(type, amount-1);
        --currentInvSize;
        return true;
    }

    /**
     * @return Current total amount of items in the inventory
     */
    public int getInventoryAmount(){
        return currentInvSize; 
    }

    public void reset(){
        this.currentMovementPoint = type.getMovementPoints();
    }

    public BufferedImage getImage(){
        return type.getImage();
    }

    /**
     * Used for comparing one unit to another, 
     * to find out if you could win, draw or lose 
     * in a fight with the other unit.
     *
     * @param other The opposing unit.
     *
     * @return Negative for a loss, 0 for a tie and positive for a win.
     */
    public int compareTo(PhysicalUnit other){
        int attacking = this.type.getAttack() - other.type.getDefence() - other.manPower;
        int defending = this.manPower - other.type.getAttack() + this.type.getDefence();
        return attacking - defending;
    }

    public boolean equals(Object other){
        if(other instanceof PhysicalUnit){
            return this.idNumber == ((PhysicalUnit)other).idNumber;
        }
        return false;
    }
}
