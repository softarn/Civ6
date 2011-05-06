package civ;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;

public class ViewPort extends JPanel{
    private GameMapView gmv;
    private static JPanel popup = new JPanel();

    public ViewPort(){
        setLayout(null);
        gmv = new GameMapView();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setBounds(size.width/4,size.height/8,700,400);
        popup.setVisible(false);

        add(popup);
        add(gmv);
    }

    public static JPanel getPopup(){
        return popup;
    }
}
