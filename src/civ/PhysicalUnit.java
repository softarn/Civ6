package civ;

import java.awt.image.BufferedImage;

public class PhysicalUnit implements Comparable<PhysicalUnit>{
    private static int count = 0;
    private int idNumber;
    private int manPower;
    private int currentMovementPoint;
    private PhysicalUnitType type;
    //private City city = null;

    public PhysicalUnit(PhysicalUnitType type){
        this.idNumber = ++count;
        this.manPower = type.getMaxManPower();
        this.currentMovementPoint = type.getMovementPoints();
        this.type = type;
    }

    public PhysicalUnit(PhysicalUnit other){
        this.idNumber = ++count;
        this.manPower = other.getManPower();
        this.currentMovementPoint = other.getCurrentMovementPoint();
        this.type = other.getType();
    }

    public boolean isInCity(){
        return false;
    }

    public PhysicalUnitType getType(){
        return type;
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
        return false;
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
