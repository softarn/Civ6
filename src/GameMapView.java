package src;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GameMapView extends JPanel{

    private GameMap gm;

    public GameMapView(){
        super();
        gm = new GameMap(this);
        setLayout(null);
        setBackground(Color.black);
    }

    private class GameMapListener implements MouseListener{
        public void mouseExited(MouseEvent e){

        }

        public void mouseEntered(MouseEvent e){

        }

        public void mouseReleased(MouseEvent e){

        }

        public void mousePressed(MouseEvent e){

        }

        public void mouseClicked(MouseEvent e){
           // (e.getX(), e.getY());
        }
    }
}
