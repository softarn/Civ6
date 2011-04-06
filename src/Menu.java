import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Observer;
import java.util.Observable;

public class Menu extends JPanel implements Observer{

    public static final State state = State.getInstance();

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getState());

    Menu(){
	super();
	state.addObserver(this);
	add(status);
    }

    public void update(Observable obs, Object obj){
	if(obs == state){
	    updateState();
	}
    }

    private void updateState(){
	status.setText("Status is: " + state.getState());
    }

}
