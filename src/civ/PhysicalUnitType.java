package civ;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public enum PhysicalUnitType implements AbstractUnitType{
    // Artillery
    Catapult(       "Catapult",     "Artillery", 100, 12, 1, 2, 1, 50, 5),
        Trebuchet(  "Trebuchet",    "Artillery", 100, 20, 2, 3, 1, 75, 7),
        Cannon(     "Cannon",       "Artillery", 100, 30, 3, 4, 1, 100, 20),

        // Range
        Archer(     "Archer",       "Range", 100, 4, 2, 2, 1, 50, 2),
        Musketeer(  "Musketeer",    "Range", 100, 8, 6, 2, 1, 50, 5),

        // Melee
        Phalanx(    "Phalanx",      "Melee", 100, 2, 5, 1, 1, 50, 3),
        Legion(     "Legion",       "Melee", 100, 6, 4, 1, 1, 75, 4),
        Infantry(   "Infantry",     "Melee", 100, 3, 3, 1, 1, 50, 3),
        Pikeman(    "Pikeman",      "Melee", 100, 2, 3, 1, 1, 50, 4),

        // Mounted 
        Cavalry(    "Cavalry",      "Mounted", 100,  6, 4, 1, 2, 100, 8),
        Knight(     "Knight",       "Mounted", 100, 12, 8, 1, 2, 100, 10),
        Crusader(   "Crusader",     "Mounted", 100,  18, 12, 1, 2, 150, 12),

        // Boats
        Trireme(    "Trireme",      "Boat", 50,   4,  3, 1, 3, 100, 16),
        Galley(     "Galley",       "Boat", 250, 30, 25, 1, 4, 400, 58),
        Caravel(    "Caravel",      "Boat", 100, 50, 40, 1, 6, 200, 70),

        // Other
        Settler(    "Settler",      "Other", 100, 0, 2, 0, 1, 100, 5), 
        Diplomat(   "Diplomat",     "Other", 25,  0, 0, 0, 3, 100, 2),
        Siegetower( "Siege Tower",  "Other", 0,   0, 0, 0, 1,  50, 6),
        Wagontrain( "Wagon Train",  "Other", 0,   0, 0, 0, 2, 100, 3);

    private UnitType unitType;
    private Hold hold;
    private int cost;

    private PhysicalUnitType(String name, 
            String category,
            int maxManPower,
            int attack,
            int defence,
            int range,
            int movementPoints,
            int inventorySize,
            int cost){
        this.unitType = new UnitType(name, category, maxManPower, attack, 
                defence, range, movementPoints, inventorySize, this);
        this.cost = cost;
        if(name.equals("Siege Tower") || name.equals("Galley") || name.equals("Caravel")){
            this.hold = new Hold();
        }
        else{
            this.hold = null;
        }

    }

    public static PhysicalUnitType getByName(String name){
        AbstractUnitType result = UnitType.getByName(name);
        if(result instanceof PhysicalUnitType){
            return (PhysicalUnitType)result;
        }
        else return null;
    }

    public String getName(){
        return unitType.getName();
    }

    public String getCategory(){
        return unitType.getCategory();
    }

    public int getAttack(){
        return unitType.getAttack();
    }

    public int getDefence(){
        return unitType.getDefence();
    }

    public int getMovementPoints(){
        return unitType.getMovementPoints();
    }

    public int getVision(){
        return unitType.getVision();
    }

    public int getMaxManPower(){
        return unitType.getMaxManPower();
    }

    public int getRange(){
        return unitType.getRange();
    }

    public Image getImage(){
        return unitType.getImage();
    }

    public int getInventorySize(){
        return unitType.getInventorySize();
    }

    public boolean isMounted(){
        return unitType.isMounted();
    }

    public Hold getHold(){
        return hold;
    }

    public int getCost(){
        return cost;
    }

    public String toString(){
        return unitType.getName();
    }
}
