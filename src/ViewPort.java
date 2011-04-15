package src;

import javax.swing.JPanel;

public class ViewPort extends JPanel{
    GameMapView gmv;
    public ViewPort(){
        setLayout(null);
        gmv = new GameMapView();
        add(gmv);
    }
}
