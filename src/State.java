import java.util.Observable;

public class State extends Observable{

    private static final State INSTANCE = new State();
    private static int stateId = 0;

    private State(){}

    public static State getInstance(){
	return INSTANCE;
    }

    public int getState(){
	return stateId;
    }

    public void setState(int state){
	stateId = state;
	setChanged();
	notifyObservers();
    }
}
