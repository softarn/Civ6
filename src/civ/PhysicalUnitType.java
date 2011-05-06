package civ;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public enum PhysicalUnitType {
    // Artillery
    Catapult("Catapult", "Artillery", 100, 12, 1, 2, 1, 50),
    Trebuchet("Trebuchet", "Artillery", 100, 20, 2, 3, 1, 75),
    Cannon("Cannon", "Artillery", 100, 30, 3, 4, 1, 100),

    // Range
    Archer("Archer", "Range", 100, 4, 2, 2, 1, 50),
    Musketeer("Musketeer", "Range", 100, 8, 6, 2, 1, 50),

    // Melee
    Phalanx("Phalanx", "Melee", 100, 2, 5, 1, 1, 50),
    Legion("Legion", "Melee", 100, 6, 4, 1, 1, 75),
    Infantry("Infantry", "Melee", 100, 3, 3, 1, 1, 50),
    Pikeman("Pikeman", "Melee", 100, 2, 3, 1, 1, 50),

    // Mounted 
    Cavalry("Cavalry", "Mounted", 100, 6, 4, 1, 2, 100),
    Knight("Knight", "Mounted", 100, 12, 8, 1, 2, 100),
    Crusader("Crusader", "Mounted", 100, 6, 4, 1, 2, 150),

    // Boats
    Trireme("Trireme", "Boat", 50, 4, 3, 1, 3, 100),
    Galley("Galley", "Boat", 250, 30, 25, 1, 4, 400),
    Caravel("Caravel", "Boat", 100, 50, 40, 1, 6, 200),

    // Other
    Settler("Settler", "Other", 100, 0, 2, 0, 1, 0),
    Diplomat("Diplomat", "Other", 25, 0, 0, 0, 3, 0),
    Siegetower("Siege Tower", "Other", 0, 0, 0, 0, 1, 0),
    Wagontrain("Wagon Train", "Other", 0, 0, 0, 0, 2, 0);

    private String name;
    private String category;
    private int maxManPower;
    private int defence;
    private int attack;
    private int range;
    private int movementPoints;
    private int vision;
    private int inventorySize;
    private boolean mounted;
    private BufferedImage unitImg;
    private Hold hold;

    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private PhysicalUnitType(String name, 
            String category,
            int maxManPower,
            int attack,
            int defence,
            int range,
            int movementPoints,
            int inventorySize){
        this.name = name;
        this.category = category;
        this.maxManPower = maxManPower;
        this.attack = attack;
        this.defence = defence;
        this.range = range;
        this.movementPoints = movementPoints;
        this.inventorySize = inventorySize;
        if(category.equals("Boat")){
            this.vision = 2;
        }
        else{
            this.vision = movementPoints;
        }
        this.mounted = category.equals("Mounted");
        if(name.equals("Siege Tower") || name.equals("Galley") || name.equals("Caravel")){
            this.hold = new Hold();
        }
        else{
            this.hold = null;
        }

        try{
            unitImg = ImageIO.read(new File(imgPath + name + ".png"));
        }catch(IOException e){
            System.out.println(e);
            System.out.println(name);
        }
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefence(){
        return defence;
    }

    public int getMovementPoints(){
        return movementPoints;
    }

    public int getVision(){
        return vision;
    }

    public int getMaxManPower(){
        return maxManPower;
    }

    public int getRange(){
        return range;
    }

    public BufferedImage getImage(){
        return unitImg;
    }

    public boolean isMounted(){
        return mounted;
    }

    public int getInventorySize(){
        return inventorySize;
    }

    public Hold getHold(){
        return hold;
    }

    public String toString(){
        return name;
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
