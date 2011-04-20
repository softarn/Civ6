package civ;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import static civ.State.TileState.TileSelected;
import static civ.State.TileState.TileUnSelected;
import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;
import static civ.State.ActionState.Move;
import static civ.State.ActionState.Attack;

import static civ.State.HoverState.HoverNone;
import static civ.State.HoverState.HoverTileOnly;

public class Menu extends JPanel implements Observer, ActionListener{

    private static final State state = State.getInstance();
    private JPanel north = new JPanel();
    
    private JButton move;
    private JButton attack;
    private JButton putUnit;

    private JComboBox selUnit;
    private JLabel tileLabel;
    private GameMapView gmv = new GameMapView();    
    
    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getUnitState());

    Menu(){ 
        super(); 
        setLayout(new BorderLayout());
        tileLabel = new JLabel("Tile info is empty"); 

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

        JPanel createUnit = new JPanel();
        createUnit.add(selUnit); 
        createUnit.add(putUnit); 

        add(createUnit, BorderLayout.WEST);
        add(status, BorderLayout.CENTER); 
        add(move, BorderLayout.SOUTH); 
        add(attack, BorderLayout.EAST); 
        add(tileLabel, BorderLayout.NORTH); 

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
                tileLabel.setText("No tile selected ");
                break;
            case HoverTileOnly:
                tileLabel.setText(state.getHoverTile().getTerrain().toString());
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
                switch(state.getActionState()){
                    case Move: move.setEnabled(false); break;
                    case Attack: attack.setEnabled(false); break;
                }
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

