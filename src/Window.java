import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import state.State;
import static state.State.UnitState.Selected;

public class Window extends JFrame{

    //Only for testpurpose
    public static void main(String[] args){
	new Window(100,100);
	State s = State.getInstance();

	try{
	    Thread.sleep(2000);
	}catch(Throwable t){}

	s.setUnitState(Selected);
    }

    Window(int w,int h){
	Menu m = new Menu();
	add(m);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(w, h);
	setVisible(true);
    }
}
