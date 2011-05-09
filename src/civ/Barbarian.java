package civ;

import java.util.Random;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

class BarbarianType implements AbstractUnitType{
    private UnitType unitType;
    public BarbarianType(){
        unitType = new UnitType("Barbarian", "Melee", 100, 3, 3, 1, 1, 75); // Borrowed infantry stats
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
}

public class Barbarian extends PhysicalUnit{
    public Tile tile;
    public Barbarian(Tile tile){
        super(new BarbarianType());
        this.tile = tile;
    }

    public void reset(){
        GameMap gm = GameMap.getInstance();
        ArrayList<Tile> neighbours = gm.getNeighbours(tile, 1, true);
        for(Tile t : neighbours){
            if(t.hasUnit()){
                Battle.doBattle(this, t.getUnit(), tile, t);
                return;
            }
        }
        Random r = new Random();
        int i = r.nextInt(neighbours.size());
        tile = neighbours.get(i);
    }
}
