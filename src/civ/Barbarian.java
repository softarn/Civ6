package civ;

import java.util.Random;
import java.util.ArrayList;
import java.awt.Image;
import java.util.Random;

import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;

class BarbarianType implements AbstractUnitType{
    private UnitType unitType;
    
    public BarbarianType(){	
    	Random rand = new Random();
    	PhysicalUnitType put;
    	do{
    		int i = rand.nextInt(PhysicalUnitType.values().length);
    		put = PhysicalUnitType.values()[i];
    		System.out.println(put.getName());
    	} while(put.getCategory() == "Other" || 
    		put.getCategory() == "Artillery" ||
    		put.getName() == "Caravel");
    	
        unitType = new UnitType("Barbarian", put.getCategory(), put.getMaxManPower(), put.getAttack(), 
        	put.getDefence(), put.getRange(), put.getMovementPoints(), put.getInventorySize(),
        	this); 
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
}

public class Barbarian extends PhysicalUnit{
    private static final State state = State.getInstance();
    public Tile tile;
    public Barbarian(Tile tile){
        super(new BarbarianType());
        this.tile = tile;
    }

    public void reset(){
        PhysicalUnit unit = null;
        if(state.getUnitState() == UnitSelected){
            unit = state.getSelectedUnit();
        }
        state.setUnitState(UnitUnSelected);
        state.setSelectedUnit(this);
        state.setUnitState(UnitSelected);
        GameMap gm = GameMap.getInstance();
        ArrayList<Tile> neighbours = gm.getNeighbours(tile, 1, false);
        currentMovementPoint = type.getMovementPoints();
        for(Tile t : neighbours){
            if(t.hasUnit()){
                Battle.doBattle(this, t.getUnit(), tile, t);
                return;
            }
        }
        Random r = new Random();
        neighbours = gm.getNeighbours(tile, 1, true);
        int i;
        try{
            i = r.nextInt(neighbours.size());
            tile.setUnit(null);
            tile = neighbours.get(i);
            tile.setUnit(this);
        }
        catch(IllegalArgumentException iae){
        }
        state.setUnitState(UnitUnSelected);
        if(unit != null){
            state.setSelectedUnit(unit);
            state.setUnitState(UnitSelected);
        }
    }
}
