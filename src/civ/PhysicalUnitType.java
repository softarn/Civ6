package civ;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public enum PhysicalUnitType {
    Musketeer("Musketeer", 100, 2, 2, 3, 2, 1); 

    private String name;
    private int maxManPower;
    private int defence;
    private int attack;
    private int hitPoint;
    private int range;
    private int movementPoints;
    private BufferedImage unitImg;

    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private PhysicalUnitType(String name, 
            int maxManPower,
            int attack,
            int defence,
            int hitPoint,
            int range,
            int movementPoints){
        this.name = name;
        this.maxManPower = maxManPower;
        this.attack = attack;
        this.defence = defence;
        this.hitPoint = hitPoint;
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

    public int getAttack(){
        return attack;
    }

    public int getDefence(){
        return defence;
    }

    public int getHitPoints(){
        return hitPoint;
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
