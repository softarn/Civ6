package civ;

import java.awt.Image;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public interface AbstractUnitType{
    abstract public String getName();
    abstract public String getCategory();
    abstract public int getAttack();
    abstract public int getDefence();
    abstract public int getMovementPoints();
    abstract public int getVision();
    abstract public int getMaxManPower();
    abstract public int getRange();
    abstract public Image getImage();
    abstract public int getInventorySize();
    abstract public boolean isMounted();
}

class UnitType implements AbstractUnitType{
    private String name;
    private String category;
    private Image unitImg;
    private int maxManPower;
    private int defence;
    private int attack;
    private int range;
    private int movementPoints;
    private int vision;
    private int inventorySize;
    private boolean mounted;

    private static final String imgPath = "/data/img/"; //Need a better fix for this!
    private static HashMap<String, AbstractUnitType> types = new HashMap<String, AbstractUnitType>();

    protected UnitType(String name, 
            String category,
            int maxManPower,
            int attack,
            int defence,
            int range,
            int movementPoints,
            int inventorySize,
            AbstractUnitType type){
        this.name = name;
        this.category = category;
        this.maxManPower = maxManPower;
        this.attack = attack;
        this.defence = defence;
        this.range = range;
        this.movementPoints = movementPoints;
        this.inventorySize = inventorySize;
        this.mounted = category.equals("Mounted");
        types.put(name, type);
        if(category.equals("Boat")){
            this.vision = 2;
        }
        else{
            this.vision = movementPoints;
        }
        try{
            unitImg = Toolkit.getDefaultToolkit().getImage((getClass().getResource(imgPath + name + ".png")));
        }catch(Exception e){
            System.out.println(e);
            System.out.println(name);
        }
    }

    public static AbstractUnitType getByName(String name){
        return types.get(name);
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

    public Image getImage(){
        return unitImg;
    }

    public int getInventorySize(){
        return inventorySize;
    }

    public boolean isMounted(){
        return mounted;
    }
}
