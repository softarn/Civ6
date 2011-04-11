package state;

import java.util.Observable;

public class State extends Observable{
    private static final State INSTANCE = new State();
    private UnitState unitState;
    private ActionState actionState;

    private State(){
	setUnitState(UnitState.UnSelected);
	setActionState(ActionState.None);
    }

    public static State getInstance(){
	return INSTANCE;
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
