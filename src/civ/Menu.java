package civ;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

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
    private JPanel createUnit = new JPanel(); // Temporary panel

    private JButton move;
    private JButton attack;
    private JButton putUnit;
    private JComboBox selUnit;

    private JProgressBar manPowerBar;    
    private JLabel tileLabel;
    private JLabel unitPresentation;
    private GameMapView gmv = new GameMapView();    

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getUnitState());

    Menu(){ 
        super(); 
        setLayout(new BorderLayout(20, 30));
        north.setLayout(new FlowLayout());

        manPowerBar = new JProgressBar(0,100);  
        manPowerBar.setSize(new Dimension(50,10));
        manPowerBar.setString("Manpower ");
        manPowerBar.setStringPainted(true);

        tileLabel = new JLabel("Tile info is empty");  
        unitPresentation = new JLabel();

        move = new JButton("Move");
        //move.setMnemonic(KeyEvent.VK_D);
        move.setActionCommand("move");
        move.addActionListener(this);

        attack = new JButton("Attack");
        attack.setActionCommand("attack");
        attack.addActionListener(this);

        selUnit = new JComboBox(PhysicalUnitType.values());
        putUnit = new JButton("Set Unit");
        putUnit.setActionCommand("setunit");
        putUnit.addActionListener(this);

        state.addObserver(this);

        createUnit.add(selUnit); 
        createUnit.add(putUnit); 

        add(createUnit, BorderLayout.WEST);

        // Intended to be the north panel    
        add(north, BorderLayout.NORTH); 
        add(eastPanel, BorderLayout.EAST);
        add(manPowerBar, BorderLayout.SOUTH);

        north.add(tileLabel);
        north.add(unitPresentation);

        //north.add(status); 
        eastPanel.add(move); 
        eastPanel.add(attack); 

        update(); 

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
                tileLabel.setText("Terrain:" + state.getHoverTile().getTerrain().toString());
                break;
            case HoverTileUnit:
                String outputTerrain = state.getHoverTile().getTerrain().toString();
                String outputUnit = Integer.toString(state.getHoverTile().getUnit().getManPower());
                tileLabel.setText("Terrain: " + outputTerrain +
                        " - Unit: " + state.getHoverTile().getUnit().getType() +
                        " Att: " + state.getHoverTile().getUnit().getType().getAttack() + 
                        " Def: " + state.getHoverTile().getUnit().getType().getDefence() +
                        " Mnp: " + outputUnit);
                break;
        }        

        switch(state.getUnitState()){
            case UnitUnSelected: 
                move.setEnabled(false); 
                attack.setEnabled(false);
                break;
            case UnitSelected: 
                move.setEnabled(true);
                attack.setEnabled(true);
            
                manPowerBar.setValue(state.getSelectedUnit().getManPower());
                manPowerBar.setString("Manpower: " + Integer.toString(state.getSelectedUnit().getManPower()));
                manPowerBar.repaint();
                // unitPresentation should contain victory chance variable 

                switch(state.getActionState()){
                    case Move: move.setEnabled(false); break;
                    case Attack: attack.setEnabled(false); break;
                }
                
                unitPresentation.setText(state.getSelectedUnit().getType().getName()+
                        (" is marked. Attack: ") +state.getSelectedUnit().getType().getAttack());
                break;
        }	
        updateState();
    }

    private void updateState(){
        status.setText("Status is: " + state.getUnitState());
    }

    public void actionPerformed(ActionEvent ae){
        if(move == ae.getSource()){
            state.setActionState(Move);
        }
        if(attack == ae.getSource()){
            state.setActionState(Attack);
        }
        if(putUnit == ae.getSource()){
            if(state.getTileState() == TileSelected){
                if(state.getSelectedTile().hasUnit()){
                    status.setText("Status is: Can't place unit on another unit.");
                }
                else{
                    state.getSelectedTile().setUnit(new PhysicalUnit((PhysicalUnitType)selUnit.getSelectedItem()));
                    state.getSelectedTile().getView().repaint();
                }
            }
        }
    }
}

