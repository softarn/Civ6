package src;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import static src.State.UnitState.Selected;

public class Window extends JFrame{

    //Only for testpurpose
    public static void main(String[] args){
        new Window(900,900);
        State s = State.getInstance();

        try{
            Thread.sleep(2000);
        }catch(Throwable t){}

        s.setUnitState(Selected);
    }

    Window(int w,int h){
        Menu m = new Menu();
        GameMapView gmv = new GameMapView();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(m, BorderLayout.EAST);
        add(gmv, BorderLayout.CENTER);
        setSize(w, h);
        setVisible(true);
    }
}
