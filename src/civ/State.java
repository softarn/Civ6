package civ;

import java.util.Observable;

public class State extends Observable{
	private static final State INSTANCE = new State();

    private static boolean online = false;
	private static Tile selectedTile;
	private static City selectedCity;
	private static PhysicalUnit selectedUnit;
	private static Tile hoverTile;
	
	private UnitState unitState;
	private CityState cityState;
	private TileState tileState;
	private ActionState actionState;
    private HoverState hoverState;
   
	private State(){
		
		setUnitState(UnitState.UnitUnSelected);
		setCityState(CityState.CityUnSelected);
		setTileState(TileState.TileUnSelected);
		setActionState(ActionState.None);
        setHoverState(HoverState.HoverNone);
	}
	
	public static State getInstance(){
		return INSTANCE;
	}

    public static void setOnline(boolean on){
        online = on;
    }

    public static boolean isOnline(){
        return online;
    }

	public static void setSelectedTile(Tile tile){
		selectedTile = tile;
	}

	public static Tile getSelectedTile(){
		return selectedTile;
	}
	
	public static void setSelectedCity(City city){
		selectedCity = city;
	}

	public static City getSelectedCity(){
		return selectedCity;
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
	
	// Citystate
	public enum CityState{
		CitySelected, CityUnSelected 
	}

	public CityState getCityState(){
		return cityState;
	}

	public void setCityState(CityState state){
		cityState = state;
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
