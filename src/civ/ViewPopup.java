package civ;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;	

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Dimension; 
import static civ.State.TileState.TileSelected;

public class ViewPopup extends JTabbedPane implements ActionListener{
	private static final State state = State.getInstance();
    private JLabel jel = new JLabel("HELLOOOOOOAH!");
	private JPanel cityPanel = new JPanel();
	private UnitTabView utv = new UnitTabView();
    private JButton putUnit;
    private JComboBox selUnit;
	
	public ViewPopup() {
		addTab("Enheter ", null, utv, "Tooltips vare her ");
        setPreferredSize(new Dimension(580,200));
	
		addTab("Städer", null, null, "Tooltips vare her ");
		addTab("Forskning", null, null, "Tooltips vare her ");
		setOpaque(true);

        selUnit = new JComboBox(PhysicalUnitType.values());
        putUnit = new JButton("Set Unit");
        putUnit.addActionListener(this);
        
		//mainTPane.addTab("Mecca", null, Content, "Tooltips vare her ");
		//add(unitContent);
		
		//add(cityPanel);
		
		//mainTPane.repaint();
		
	}
	
	public void setTab(){

		cityPanel.add(jel);
		
		cityPanel.add(selUnit);
        cityPanel.add(putUnit);
        
		//add(cityPanel);
		addTab("Mecca",null,cityPanel,"Visa mer om city");
		setSelectedIndex(3);
		
		
	}
		
	public void actionPerformed(ActionEvent ae){
        if(putUnit == ae.getSource()){
            if(state.getTileState() == TileSelected){
                if(state.getSelectedTile().hasUnit()){
                    //tileLabel.setText("<html><br />Can't place unit on another unit.</html>");
                    GameMap gm = GameMap.getInstance();
                }
                else if(!state.getSelectedTile().getTerrain().isTraversible((PhysicalUnitType)selUnit.getSelectedItem())){
                    //tileLabel.setText("<html><br />Unit can't stand there.</html>");
                }
                else{
                    Tile tile = state.getSelectedTile();
                    PhysicalUnitType type = (PhysicalUnitType)selUnit.getSelectedItem();
                    Round.spawnCounter(type, tile, type.getCost());
                }
            }
        }
    }
}
