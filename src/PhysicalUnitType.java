package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public enum PhysicalUnitType {
    // Artillery
    Catapult("Catapult", "Artillery", 100, 12, 1, 2, 1),
    Trebuchet("Trebuchet", "Artillery", 100, 20, 2, 3, 1),
    Cannon("Cannon", "Artillery", 100, 30, 3, 4, 1),

    // Range
    Archer("Archer", "Range", 100, 4, 2, 2, 1),
    Musketeer("Musketeer", "Range", 100, 8, 6, 2, 1),

    // Melee
    Phalanx("Phalanx", "Melee", 100, 2, 5, 1, 1),
    Legion("Legion", "Melee", 100, 6, 4, 1, 1),
    Infantry("Infantry", "Melee", 100, 3, 3, 1, 1),
    Pikeman("Pikeman", "Melee", 100, 2, 3, 1, 1),

    // Horseborn 
    Cavalry("Cavalry", "Mounted", 100, 6, 4, 1, 2),
    Knight("Kight", "Mounted", 100, 12, 8, 1, 2),
    Crusader("Crusader", "Mounted", 100, 6, 4, 1, 2),

    // Boats
    Trireme("Trireme", "Boat", 50, 4, 3, 1, 3),
    Galley("Galley", "Boat", 250, 30, 25, 1, 4),
    Caravel("Caravel", "Boat", 100, 50, 40, 1, 6);


    private String name;
    private String category;
    private int maxManPower;
    private int defence;
    private int attack;
    private int range;
    private int movementPoints;
    private BufferedImage unitImg;

    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private PhysicalUnitType(String name, 
            String category,
            int maxManPower,
            int attack,
            int defence,
            int range,
            int movementPoints){
        this.name = name;
        this.category = category;
        this.maxManPower = maxManPower;
        this.attack = attack;
        this.defence = defence;
        this.range = range;
        this.movementPoints = movementPoints;

        try{
            unitImg = ImageIO.read(new File(imgPath + name + ".png"));
        }catch(IOException e){
            System.out.println(e);
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

    public int getMaxManPower(){
        return maxManPower;
    }

    public int getRange(){
        return range;
    }

    public BufferedImage getImage(){
        return unitImg;
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
