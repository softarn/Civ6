package civ;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public enum PhysicalUnitType implements AbstractUnitType{
    // Artillery
    Catapult(       "Catapult",     "Artillery", 100, 12, 1, 2, 1, 50),
        Trebuchet(  "Trebuchet",    "Artillery", 100, 20, 2, 3, 1, 75),
        Cannon(     "Cannon",       "Artillery", 100, 30, 3, 4, 1, 100),

        // Range
        Archer(     "Archer",       "Range", 100, 4, 2, 2, 1, 50),
        Musketeer(  "Musketeer",    "Range", 100, 8, 6, 2, 1, 50),

        // Melee
        Phalanx(    "Phalanx",      "Melee", 100, 2, 5, 1, 1, 50),
        Legion(     "Legion",       "Melee", 100, 6, 4, 1, 1, 75),
        Infantry(   "Infantry",     "Melee", 100, 3, 3, 1, 1, 50),
        Pikeman(    "Pikeman",      "Melee", 100, 2, 3, 1, 1, 50),

        // Mounted 
        Cavalry(    "Cavalry",      "Mounted", 100,  6, 4, 1, 2, 100),
        Knight(     "Knight",       "Mounted", 100, 12, 8, 1, 2, 100),
        Crusader(   "Crusader",     "Mounted", 100,  6, 4, 1, 2, 150),

        // Boats
        Trireme(    "Trireme",      "Boat", 50,   4,  3, 1, 3, 100),
        Galley(     "Galley",       "Boat", 250, 30, 25, 1, 4, 400),
        Caravel(    "Caravel",      "Boat", 100, 50, 40, 1, 6, 200),

        // Other
        Settler(    "Settler",      "Other", 100, 0, 2, 0, 1, 100),
        Diplomat(   "Diplomat",     "Other", 25,  0, 0, 0, 3, 100),
        Siegetower( "Siege Tower",  "Other", 0,   0, 0, 0, 1,  50),
        Wagontrain( "Wagon Train",  "Other", 0,   0, 0, 0, 2, 100);

    private UnitType unitType;
    private Hold hold;

    private PhysicalUnitType(String name, 
            String category,
            int maxManPower,
            int attack,
            int defence,
            int range,
            int movementPoints,
            int inventorySize){
        this.unitType = new UnitType(name, category, maxManPower, attack, 
                defence, range, movementPoints, inventorySize, this);
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

    public BufferedImage getImage(){
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

    public String toString(){
        return unitType.getName();
    }

    /**
     * Used for comparing one unittype to another, 
     * to check if it's stronger than the other.
     *
     * @param other The other unittype.
     *
     * @return 
     public int compareTo(PhysicalUnitType other){
     int attacking = this.attack - other.defence;
     int defending = other.attack - this.defence;
     return attacking - defending;
     }

    /**
     * If the unittype names are equal the unittypes are the same
     * this method tests that connection.
     *
     * @param other Another PhysicalUnitType to compare to.
     *
     * @return True if this name is equal to other's name
     public boolean equals(Object other){
     if(other instanceof PhysicalUnitType){
     return this.name.equals(((PhysicalUnitType)other).name);
     }
     return false;
     }
     */
}
