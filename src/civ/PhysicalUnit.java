package civ;

import java.awt.Image;
import java.util.HashMap;

import proxy.Unit;

public class PhysicalUnit implements Comparable<PhysicalUnit>{
    private static int count = 0;
    private int idNumber;
    private int manPower;
    protected int currentMovementPoint;
    protected AbstractUnitType type;
    private PhysicalUnitView view;
    private Player allegiance;
    //private HashMap<ResourceType, Integer> inventory;
    private int currentInvSize;
    private boolean fortified;
    private Hold hold;

    /** Used to create barbarians */
    public PhysicalUnit(BarbarianType type){ 
        this.idNumber = ++count;
        this.type = type;
        this.allegiance = null;
        this.currentMovementPoint = type.getMovementPoints();
        this.fortified = false;
        this.currentInvSize = type.getInventorySize();
        this.manPower = type.getMaxManPower();
        this.view = new PhysicalUnitView(this);
    }

    public PhysicalUnit(String type, Player allegiance){
        this(PhysicalUnitType.getByName(type), allegiance);
    }

    public PhysicalUnit(AbstractUnitType type, Player allegiance){
        this.idNumber = ++count;
        this.type = type;
        this.allegiance = allegiance;
        //this.inventory = new HashMap<ResourceType, Integer>();
        reset();
        this.currentInvSize = type.getInventorySize();
        this.manPower = type.getMaxManPower();
        this.view = new PhysicalUnitView(this);
        if(type.getName().equals("Siege Tower") || type.getName().equals("Galley") || type.getName().equals("Caravel")){
            this.hold = new Hold();
        }
        else{
            this.hold = null;
        }
    }

    public PhysicalUnit(PhysicalUnit other){
        this.idNumber = other.idNumber;
        this.manPower = other.getManPower();
        this.currentMovementPoint = other.getCurrentMovementPoint();
        this.type = other.getType();
        //this.inventory =  new HashMap<ResourceType, Integer>(other.inventory);
        this.currentInvSize = other.currentInvSize;
        this.allegiance = other.allegiance;
        this.view = other.getView();
        this.hold = other.hold;
    }

    /**
     * This is not a copy constructor, it a translator between the proxy units,
     * and our physicalunits.
     */
    public PhysicalUnit(Unit other){
        this.idNumber = ++count;
        this.type = PhysicalUnitType.getByName(other.getType());
        this.allegiance = Player.getInstance(other.getOwner());
        reset();
        this.currentInvSize = type.getInventorySize();
        this.manPower = other.getManPower();
        this.view = new PhysicalUnitView(this);
    }

    public Hold getHold(){
        return hold;
    }

    public boolean isAlly(){
        if(allegiance == null){
            return false;
        }
        return allegiance.isMe();
    }

    public Player getOwner(){
        return allegiance;
    }

    public boolean isInCity(){
        return false;
    }

    public AbstractUnitType getType(){
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

    public boolean isFortified(){
        return fortified;
    }

    public void fortify(){
        this.fortified = true;
    }

    public boolean inSiegeTower(){
        return false; // !! change later !!
    }

    public boolean useMovementPoints(int point){
        if(currentMovementPoint >= point){
            currentMovementPoint -= point;
            return true;
        }
        return false; // No more movementpoints
    }

    @Deprecated // No more resources
        public boolean addItem(){ //ResourceUnit item){
            return false; 
            /*
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
               return true;*/
        }

        /**
         * Uses one item of the resource type in the inventory of the unit.
         *
         * @param type The type of resource you want to use
         *
         * @return True if the resourcetype was available and was used, otherwise false.
         */
        public boolean useItem(){ //ResourceType type){
            if(currentInvSize == 0){
                return false; // Time to die
            }
            --currentInvSize;
            return true;
            /*
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
               */
        }

        /**
         * @return Current total amount of items in the inventory
         */
        public int getInventoryAmount(){
            return currentInvSize; 
        }

        public void reset(){
            this.currentMovementPoint = type.getMovementPoints();
            this.fortified = false;
        }

        public Image getImage(){
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
