package civ;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory   ;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.*;

import static civ.State.TileState.TileSelected;
import static civ.State.TileState.TileUnSelected;
import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;
import static civ.State.ActionState.Move;
import static civ.State.ActionState.Attack;

import static civ.State.HoverState.HoverNone;
import static civ.State.HoverState.HoverTileOnly;
import static civ.State.HoverState.HoverTileUnit;

public class Menu extends JPanel implements Observer, ActionListener{

    private static final State state = State.getInstance();
    private JPanel north = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    //private JPanel createUnit = new JPanel(); // Temporary panel
    //private JPanel tabContent = new JPanel();
    
    //private JButton putUnit;
    //private JComboBox selUnit;
    //private JComboBox selPlayer;
    private JTabbedPane tabbedPane;
    private GlobalView globalViewObject;
    //private PhysicalUnitView puvObject;
    
    private PhysicalUnitView unitView;	
    private JProgressBar manPowerBar;    
    private JLabel tileLabel;
    private JLabel unitPresentation;
    private GameMapView gmv = new GameMapView();    

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getUnitState());

    private int curScale = 5 ;
    private int[] sizes = {50, 75, 90, 120, 150, 175, 190};
    //private int scienceValue = 30;
    
    Menu(){ 
        super(); 
        setLayout(new BorderLayout(0,10)); 
        
        manPowerBar = new JProgressBar(0,100);  
        manPowerBar.setSize(new Dimension(30,10));
        manPowerBar.setString("Manpower "); 
        manPowerBar.setStringPainted(true); 
        
        unitPresentation = new JLabel();
        tileLabel = new JLabel();
    
/*
        selUnit = new JComboBox(PhysicalUnitType.values());
        selPlayer = new JComboBox(Round.getPlayers());
        putUnit = new JButton("Set Unit");
        putUnit.setActionCommand("setunit");
        putUnit.addActionListener(this);
*/
        tabbedPane = new JTabbedPane(); 
        //tabbedPane.addTab("Cities ", null, tabContent, "Your cities ");
        //stabbedPane.addTab("Units", null, tabContent, "Your units ");
       
        add(north, BorderLayout.NORTH);      
        add(westPanel,  BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        
        // Set layout managers 
        north.setLayout(new BorderLayout()); 
        eastPanel.setLayout(new BorderLayout());
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension (540,200));
        
        // ADD CONTENT
        unitView = new PhysicalUnitView(GameMap.getInstance().getTile(1,1).getUnit());
        globalViewObject = new GlobalView();
        
        tabbedPane.addTab("Enhet ",null, unitView, "Enhetsegenskaper");
        tabbedPane.addTab("Cities ",null,null, "About cities");
        tabbedPane.setPreferredSize(new Dimension(540,200));
            
        // East
        eastPanel.add(globalViewObject, BorderLayout.EAST);
        
        // West
        westPanel.add(tabbedPane, BorderLayout.WEST);
        
        north.add(tileLabel);
        north.add(unitPresentation);
        
        state.addObserver(this);
        update(); 
    }

    private void scaleUp(){
        if(curScale != sizes.length-1){
            GameMap.getInstance().resize(sizes[++curScale]);
        }
    }
    private void scaleDown(){
        if(curScale != 0){
            GameMap.getInstance().resize(sizes[--curScale]);
        }
    }

    public void update(Observable obs, Object obj){
        if(obs == state){
            update();
        }
    }
    private void update(){
        switch (state.getHoverState()) {
            case HoverNone:
                tileLabel.setText("No tile");
                break;
            case HoverTileOnly:
                tileLabel.setText("Terrain: \n" + state.getHoverTile().getTerrain().toString() +" "+ 
            state.getHoverTile().getTerrain().getResources());
                break;
            case HoverTileUnit:
                String outputTerrain = state.getHoverTile().getTerrain().toString();
                String outputUnit = Integer.toString(state.getHoverTile().getUnit().getManPower());
                if(state.getHoverTile().getUnit().isAlly()){
                    tileLabel.setText("<html>Terrain: " + outputTerrain +
                            "<br>Unit: " + state.getHoverTile().getUnit().getType() +
                            " Anfall: " + state.getHoverTile().getUnit().getType().getAttack() + 
                            " Försvar: " + state.getHoverTile().getUnit().getType().getDefence() + 
                            " Manpower: " + outputUnit + "</html>");
                }
                else{
                    // Enemy unit hovered
                    tileLabel.setText("<html>Terrain: " + outputTerrain +
                            "<br>Unit: Enemy unit");

                }
                break;
        }        

        switch(state.getUnitState()){
            case UnitUnSelected: 
                
                unitView.removeAll();
                unitView.repaint();

                manPowerBar.setValue(0);
                manPowerBar.setString("Manpower: 0");
                manPowerBar.repaint();
                break;
            case UnitSelected: 
                if(state.getSelectedUnit().isAlly()){
                    /*if(state.getSelectedUnit().getCurrentMovementPoint() > 0){
                        move.setEnabled(true);
                        attack.setEnabled(true);
                    }*/
                    unitView.add(state.getSelectedUnit().getView());
                    state.getSelectedUnit().getView().update();
                    unitView.repaint();
                    manPowerBar.setValue(state.getSelectedUnit().getManPower());
                    manPowerBar.setString("Manpower: " + Integer.toString(state.getSelectedUnit().getManPower()));
                    manPowerBar.repaint();

                    /*switch(state.getActionState()){
                        case Move: move.setEnabled(false); break;
                        case Attack: attack.setEnabled(false); break;
                    }*/

                }
                break;
        }	
        updateState();
    }

    private void updateState(){
        status.setText("Status is: " + state.getUnitState());
    }

    public void actionPerformed(ActionEvent ae){
        /*
        if(move == ae.getSource()){
            state.setActionState(Move);
            if(state.getUnitState() == UnitSelected){
                    state.getSelectedUnit().getView().update();
            }
        }
        if(attack == ae.getSource()){
            state.setActionState(Attack);
            if(state.getUnitState() == UnitSelected){
                state.getSelectedUnit().getView().update();
            }
        }
        if(endturn == ae.getSource()){
            Round.next();
            GameMap.getInstance().exploreMap();
            if(state.getUnitState() == UnitSelected){
                state.getSelectedUnit().getView().update();
            }
        }
        */
    }
}

