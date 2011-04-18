package src;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

import static src.State.ActionState.Move;
import static src.State.ActionState.None;
import static src.State.UnitState.Selected;

import static src.State.HoverState.HoverNone;
import static src.State.HoverState.HoverTileOnly;   

public class GameMapView extends JPanel{
    
    private GameMap gm;
    private State state = State.getInstance();

    public GameMapView(){
        super();
        gm = new GameMap(this);
        setLayout(null);
        setBounds(-120*(gm.getWidth()-1),0,125*gm.getWidth()*2,140*gm.getHeight());
        setBackground(Color.black);
        GameMapListener gml = new GameMapListener();
        addMouseListener(gml);
        addMouseMotionListener(gml);
    }

    private class GameMapListener implements MouseListener, MouseMotionListener{
        private int saveX=0, saveY=0;
        public void mouseDragged(MouseEvent e){
            if(SwingUtilities.isRightMouseButton(e)){
                int tempX = getX() + e.getX() - saveX;
                int tempY = getY() + e.getY() - saveY;
                if(tempX > 0){
                    tempX = 0;
                }
                if(tempX + getWidth() < getParent().getWidth()){
                    tempX = getParent().getWidth() - getWidth();
                }
                if(tempY > 0){
                    tempY = 0;
                }
                if(tempY + getHeight() < getParent().getHeight()){
                    tempY = getParent().getHeight() - getHeight();
                }

                setBounds(tempX, tempY, getWidth(), getHeight());
            }
        }
        
        public void mouseReleased(MouseEvent e){
            if(e.getButton() == MouseEvent.BUTTON3){
                saveX = 0;
                saveY = 0;
            }
        }

        public void mousePressed(MouseEvent e){
            if(e.getButton() == MouseEvent.BUTTON1){
                Tile tile = gm.getTileAt(e.getX(), e.getY());
                if(tile != null){
                    System.out.println("Printing hexagon at x"+ tile.getView().getTilePositionx()+
                            " y"+tile.getView().getTilePositiony());
                    switch(state.getActionState()){
                        case Move:
                            src.Move.makeMove(state.getSelectedTile(), tile);
                            state.setActionState(None);
                            break;
                        case Attack:
                            if(state.getUnitState() == Selected && tile.hasUnit()){
                                System.out.println(state.getSelectedUnit().getManPower() + " and " + 
                                        tile.getUnit().getManPower());
                                System.out.println(Battle.doBattle(state.getSelectedUnit(), tile.getUnit(), 
                                            state.getSelectedTile(), tile));
                                state.setActionState(None);
                                tile = state.getSelectedTile();
                            }
                            else{
                                System.out.println("No unit to attack here.");
                            }
                            break;
                    }
                    tile.select(); 
                }
            }
            else if(e.getButton() == MouseEvent.BUTTON3){
                saveX = e.getX();
                saveY = e.getY();
            }
        }

        public void mouseClicked(MouseEvent e){

        }

        public void mouseMoved(MouseEvent e){
        
        }

        public void mouseExited(MouseEvent e){
        
        }

        public void mouseEntered(MouseEvent e){
            Tile tile = gm.getTileAt(e.getX(), e.getY());
            if (tile == null){
                state.setHoverTile(null);
                state.setHoverState(HoverNone);
            }
            else if (tile != state.getHoverTile()) {
                state.setHoverTile(tile);
                state.setHoverState(HoverTileOnly);
            }	
        }
    } 
}// class
