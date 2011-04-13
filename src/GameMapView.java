package src;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import static src.State.ActionState.Move;
import static src.State.ActionState.None;

public class GameMapView extends JPanel{

    private GameMap gm;
    private State state = State.getInstance();

    public GameMapView(){
        super();
        gm = new GameMap(this);
        setLayout(null);
        setBackground(Color.black);
        addMouseListener(new GameMapListener());
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
            Tile tile = gm.getTileAt(e.getX(), e.getY());
            System.out.println("Printing hexagon at x"+ tile.getView().getTilePositionx()+
                    " y"+tile.getView().getTilePositiony());
            if(tile != null){
                if(state.getActionState() == Move){
                    src.Move.makeMove(state.getSelectedTile(), tile);
                    state.setActionState(None);
                }

                tile.select(); 
            }
        }
    }
}
