import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JPanel implements Observer, ActionListener{

    private static final State state = State.getInstance();

    private JButton move;

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getState());

    Menu(){
	super();

	move = new JButton("Move");
	//move.setMnemonic(KeyEvent.VK_D);
	move.setActionCommand("move");
	move.addActionListener(this);

	state.addObserver(this);
	add(status);
	add(move);

	update();
    }

    public void update(Observable obs, Object obj){
	if(obs == state){
	    update();
	}
    }

    private void update(){
	switch(state.getState()){
	    case 0: move.setEnabled(false); break;
	    case 1: move.setEnabled(true); break;
	    case 2: move.setEnabled(true); break;
	}
	updateState();
    }

    private void updateState(){
	status.setText("Status is: " + state.getState());
    }

    public void actionPerformed(ActionEvent ae){
	if(move == ae.getSource()){
	    state.setState(2);
	}
    }

}
