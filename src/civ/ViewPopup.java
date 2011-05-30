package civ;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;	
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import java.awt.Color;
import java.awt.Dimension; 
import static civ.State.TileState.TileSelected;

public class ViewPopup extends JTabbedPane implements ActionListener{
	private static final State state = State.getInstance();
	private JPanel cityPanel = new JPanel();
	private UnitTabView utv = new UnitTabView();
    
	private JButton putUnit;
    private JComboBox selUnit;
	private JLabel selHeader;
	
	private PhysicalUnitType type;
	private Tile tile;
	
	public ViewPopup(){
	    setPreferredSize(new Dimension(580,200));
	
		addTab("Enheter ", null, utv, "Tooltips vare her ");
		//addTab("Städer", null, null, "Tooltips vare her ");
		//addTab("Forskning", null, null, "Tooltips vare her ");
		setOpaque(true);
		
		selUnit = new JComboBox(PhysicalUnitType.values());
        putUnit = new JButton("Skapa enhet");
        putUnit.addActionListener(this);
        
        selHeader = new JLabel("Välj enhet du vill skapa ");
        
		//mainTPane.addTab("Mecca", null, Content, "Tooltips vare her ");
		//add(unitContent);
		
		//add(cityPanel);
		
		//mainTPane.repaint();
		
	}
	
	public void setTab(){
		cityPanel.removeAll();
		cityPanel.add(selHeader);
		cityPanel.add(Box.createRigidArea(new Dimension(30,30)));
		cityPanel.add(selUnit);
		cityPanel.add(Box.createRigidArea(new Dimension(50,50)));
        cityPanel.add(putUnit);
        
        cityPanel.repaint();
        
		//add(cityPanel);
		addTab(state.getSelectedTile().getCity().getName(),null,cityPanel,"Visa mer om city");
		setSelectedIndex(1);
		
	}
		
	public void actionPerformed(ActionEvent ae){
        if(putUnit == ae.getSource()){
            if(state.getTileState() == TileSelected){
                tile = state.getSelectedTile();
                type = (PhysicalUnitType)selUnit.getSelectedItem();
                tile.getCity().spawnCounter(type, tile, type.getCost());
                
                unitBuilding(type);
             	ViewPort.getPopup().setVisible(false);   
            }
        }
    }
    
    public void unitBuilding(PhysicalUnitType type){
    	this.type = type;
    	tile.getCity().getView().setUnitBuilding(type);
    	System.out.println("NU bygger vi: " + type.getName());
    }
    
}
