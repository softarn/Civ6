package civ;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public interface AbstractUnitType{
    abstract public String getName();
    abstract public String getCategory();
    abstract public int getAttack();
    abstract public int getDefence();
    abstract public int getMovementPoints();
    abstract public int getVision();
    abstract public int getMaxManPower();
    abstract public int getRange();
    abstract public BufferedImage getImage();
    abstract public int getInventorySize();
    abstract public boolean isMounted();
}

class UnitType implements AbstractUnitType{
    private String name;
    private String category;
    private BufferedImage unitImg;
    private int maxManPower;
    private int defence;
    private int attack;
    private int range;
    private int movementPoints;
    private int vision;
    private int inventorySize;
    private boolean mounted;

    private static final String imgPath = "data/img/"; //Need a better fix for this!

    protected UnitType(String name, 
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
        this.mounted = category.equals("Mounted");
        if(category.equals("Boat")){
            this.vision = 2;
        }
        else{
            this.vision = movementPoints;
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

    public int getInventorySize(){
        return inventorySize;
    }

    public boolean isMounted(){
        return mounted;
    }
}
