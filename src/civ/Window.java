package civ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Window extends JFrame{

    //Only for testpurpose
    public static void main(String[] args){
        new Window(600,600);
    }

    Window(int w,int h){
        super ("Civ 6");
        Menu m = new Menu();
        ViewPort vp = new ViewPort();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(m, BorderLayout.SOUTH);
        add(vp, BorderLayout.CENTER);
        setSize(w, h);
        setVisible(true);
    }
}
