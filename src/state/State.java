package state;

import java.util.Observable;

public class State extends Observable{
    private static final State INSTANCE = new State();

    private static PhysicalUnit selectedUnit;
    private static Tile selectedTile;

    private UnitState unitState;
    private TileState tileState;
    private ActionState actionState;

    private State(){
        setUnitState(UnitState.UnSelected);
        setActionState(ActionState.None);
    }

    public static State getInstance(){
        return INSTANCE;
    }

    public static void setSelectedTile(Tile tile){
        selectedTile = tile;
    }

    public static Tile getSelectedTile(){
        return tile;
    }

    public static PhysicalUnit getSelectedUnit(){
        return selectedUnit;
    }

    //Unitstate
    public enum UnitState{
        Selected, UnSelected 
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
        Selected, Unselected
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
        None, Move 
    }

    public ActionState getActionState(){
        return actionState;
    }

    public void setActionState(ActionState state){
        actionState = state;
        setChanged();
        notifyObservers();
    }
}
