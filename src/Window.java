import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame{

    //Only for testpurpose
    public static void main(String[] args){
	new Window(100,100);
	State s = State.getInstance();

	try{
	    Thread.sleep(2000);
	}catch(Throwable t){}

	s.setState(1);
    }

    Window(int w,int h){
	Menu m = new Menu();
	add(m);
	setSize(w, h);
	setVisible(true);
    }
}
