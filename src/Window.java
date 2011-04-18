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
    }

    Window(int w,int h){
        Menu m = new Menu();
        ViewPort vp = new ViewPort();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(m, BorderLayout.EAST);
        add(vp, BorderLayout.CENTER);
        setSize(w, h);
        setVisible(true);
    }
}
