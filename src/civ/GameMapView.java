package civ;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import static civ.State.ActionState.Move;
import static civ.State.ActionState.None;
import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;
import static civ.State.CityState.CitySelected;
import static civ.State.HoverState.HoverNone;
import static civ.State.HoverState.HoverTileOnly;
import static civ.State.HoverState.HoverTileUnit;
import static civ.State.HoverState.HoverTileCity;

public class GameMapView extends JPanel{

    private GameMap gm;
    private State state = State.getInstance();

    public GameMapView(){
        super();
        gm = GameMap.getInstance();
        gm.init(this);
        setLayout(null);
        setBounds(-105*(gm.getWidth()),-190,140*gm.getWidth()*2,170*gm.getHeight());
        setBackground(Color.black);
        GameMapListener gml = new GameMapListener();
        addMouseListener(gml);
        addMouseMotionListener(gml);
    }

    public void centerOn(Tile t){
        // Calculate the center of the tile
        centerOn(t.getView().getX()+t.getView().getWidth()/2, 
                t.getView().getY()+t.getView().getHeight()/2);
    }
    public void centerOn(int x, int y){
        // The coordinates for the map where the 
        // point (x,y) is centered according to the viewport.
        int tempX = getParent().getWidth()/2-x;
        int tempY = getParent().getHeight()/2-y;
        if(tempX > 0) {
            tempX = 0;
        }
        if(tempX + getWidth() < getParent().getWidth()){
            tempX = getParent().getWidth() - getWidth();
        }
        if(tempY > 0) {
            tempY = 0;
        }
        if(tempY + getHeight() < getParent().getHeight()){
            tempY = getParent().getHeight() - getHeight();
        }
        setBounds(tempX, tempY, getWidth(), getHeight());
    }

    private class GameMapListener implements MouseListener, MouseMotionListener{
        private int saveX=0, saveY=0;
        JPanel before, after;
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
                if(after != null){
                    GameMapView.this.remove(after);
                    before = null;
                    after = null;
                }
                Tile tile = gm.getTileAt(e.getX(), e.getY());
                if(tile != null){
                    System.out.println("Printing hexagon at x"+ tile.getView().getTilePositionx()+
                            " y"+tile.getView().getTilePositiony());

                    switch(state.getActionState()){
                        case Move:
                            if(state.getCityState() == CitySelected){
                                City city = state.getSelectedCity();
                                PhysicalUnit unit = state.getHoldUnit();
                                if(unit != null){
                                    for(Tile t : gm.getNeighbours(state.getSelectedTile(), unit.getCurrentMovementPoint(), true)){
                                        t.dehilight();
                                        t.getView().repaint();
                                    }
                                    if(civ.Move.makeMove(unit, state.getSelectedTile(), tile)){
                                        city.getHold().delUnit(unit);
                                    }
                                }
                                state.setHoldUnit(null);
                            }
                            else if(state.getUnitState() == UnitSelected){
                                PhysicalUnit pu = state.getSelectedUnit();
                                PhysicalUnit unit = state.getHoldUnit();
                                int movement = pu.getCurrentMovementPoint();
                                for(Tile t : gm.getNeighbours(state.getSelectedTile(), movement, true)){
                                    t.dehilight();
                                    t.getView().repaint();
                                }
                                if(unit != null){
                                    if(pu.getHold() != null){
                                        if(civ.Move.makeMove(unit, state.getSelectedTile(), tile)){
                                            pu.getHold().delUnit(unit);
                                        }
                                    }
                                    else{
                                        civ.Move.makeMove(state.getSelectedTile(), tile);
                                    }
                                }
                                else{
                                    civ.Move.makeMove(state.getSelectedTile(), tile);
                                }
                                state.setHoldUnit(null);
                            }
                            state.setActionState(None);
                            break;
                        case Attack:
                            if(state.getUnitState() == UnitSelected){
                                PhysicalUnit pu = state.getSelectedUnit();
                                int range = pu.getType().getRange();
                                for(Tile t : gm.getNeighbours(state.getSelectedTile(), range, false)){
                                    t.dehilight();
                                    t.getView().repaint();
                                }
                                if(tile.hasUnit() && !tile.getUnit().isAlly()){
                                    if(Battle.attackRange(state.getSelectedTile(), tile, 
                                                state.getSelectedUnit().getType().getRange()) > 0){
                                        before = new PopUpBubble(state.getSelectedTile().getView().getX() + 135, 
                                                state.getSelectedTile().getView().getY() + 35);
                                        GameMapView.this.add(before, 0);
                                        GameMapView.this.repaint();
                                        showConfirmAttackPane(state.getSelectedUnit(), tile.getUnit(), 
                                                state.getSelectedTile(), tile);
                                        Battle.doBattle(state.getSelectedUnit(), tile.getUnit(), 
                                                state.getSelectedTile(), tile);
                                        after = new PopUpBubble(state.getSelectedTile().getView().getX() + 135, 
                                                state.getSelectedTile().getView().getY() + 35, 
                                                Battle.getAttackerLoss(), Battle.getDefenderLoss());
                                        GameMapView.this.remove(before);
                                        GameMapView.this.add(after, 0);
                                        GameMapView.this.repaint();

                                    }
                                }
                                else{
                                    System.out.println("No unit to attack here.");
                                    state.setActionState(None);
                                }
                                state.setActionState(None);
                                tile = state.getSelectedTile();
                            }
                            else if(state.getCityState() == CitySelected){
                                City city = state.getSelectedCity();
                                int index = city.getHold().getSelUnitIndex();
                                PhysicalUnit unit = null;
                                if(index != -1){
                                    unit = city.getHold().getUnit(index);
                                }
                                for(Tile t : gm.getNeighbours(state.getSelectedTile(), unit.getType().getRange(), false)){
                                    t.dehilight();
                                    t.getView().repaint();
                                }
                                System.out.println("Can't attack from city");
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
            Tile tile = gm.getTileAt(e.getX(), e.getY());
            if (tile == null){
                state.setHoverTile(null);
                state.setHoverState(HoverNone);
            }
            else if (tile != state.getHoverTile() && tile.hasUnit() && tile.isExplored()){
                state.setHoverTile(tile); 
                state.setHoverState(HoverTileUnit); 
            } 
            else if (tile != state.getHoverTile() && tile.hasCity() && tile.isExplored()){
                state.setHoverTile(tile);
                state.setHoverState(HoverTileCity); 
            } 
            else if (tile != state.getHoverTile() && tile.isExplored()){
                state.setHoverTile(tile); 
                state.setHoverState(HoverTileOnly); 
            }
        }

        public void mouseExited(MouseEvent e){

        }

        public void mouseEntered(MouseEvent e){
        }	
    } 

    private int showConfirmAttackPane(PhysicalUnit att, PhysicalUnit def, Tile tatt, Tile tdef){
        PhysicalUnit tmpAtt = new PhysicalUnit(att);
        PhysicalUnit tmpDef = new PhysicalUnit(def);
        Battle.doAverageBattle(tmpAtt, tmpDef, tatt, tdef);

        JOptionPane.showMessageDialog(
                null,
                "You: " + tmpAtt.getManPower() + "\nDefender: " + tmpDef.getManPower() +
                "\n Attack?",
                "Expected outcome",
                JOptionPane.OK_OPTION);
        return 0;
    }
}// class
