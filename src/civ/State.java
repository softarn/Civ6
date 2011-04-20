package civ;

import java.util.Observable;

public class State extends Observable{
	private static final State INSTANCE = new State();

	private static PhysicalUnit selectedUnit;
	private static Tile selectedTile;
	private static Tile hoverTile;
	
	private UnitState unitState;
	private TileState tileState;
	private ActionState actionState;
    private HoverState hoverState;
   
	private State(){
		setUnitState(UnitState.UnitUnSelected);
		setTileState(TileState.TileUnSelected);
		setActionState(ActionState.None);
        setHoverState(HoverState.HoverNone);
	}
	
	public static State getInstance(){
		return INSTANCE;
	}

	public static void setSelectedTile(Tile tile){
		selectedTile = tile;
	}

	public static Tile getSelectedTile(){
		return selectedTile;
	}

	public static void setSelectedUnit(PhysicalUnit unit){
		selectedUnit = unit;
	}

	public static PhysicalUnit getSelectedUnit(){
		return selectedUnit;
	}
    
    public void setHoverTile(Tile t){
        hoverTile = t;    
    }
    
    public Tile getHoverTile() {
        return hoverTile;
    }

	//Unitstate
	public enum UnitState{
		UnitSelected, UnitUnSelected 
	}

	public UnitState getUnitState(){
		return unitState;
	}

	public void setUnitState(UnitState state){
		unitState = state;
		setChanged();
		notifyObservers();
	}

	//TileState
	public enum TileState{
		TileSelected, TileUnSelected
	}

	public TileState getTileState(){
		return tileState;
	}

	public void setTileState(TileState state){
		tileState = state;
		setChanged();
		notifyObservers();
	}

	//ActionsState
	public enum ActionState{
		None, Move, Attack
	}

	public ActionState getActionState(){
		return actionState;
	}

	public void setActionState(ActionState state){
		actionState = state;
		setChanged();
		notifyObservers();
	}
    
    // HoverState 
	public enum HoverState {
        HoverNone, HoverTileOnly, HoverTileUnit 
    } 
    
    public HoverState getHoverState (){
        return hoverState;
    }
    
    public void setHoverState(HoverState state) {
        hoverState = state;
        setChanged();
		notifyObservers();
    }
}
