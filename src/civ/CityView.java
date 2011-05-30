package civ;

import java.awt.Color;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import javax.swing.Popup;
import javax.swing.PopupFactory;
//import javax.lang.NullPointerException;

import java.util.Observer;
import java.util.Observable;

import static civ.State.ActionState.Move;
import static civ.State.ActionState.Attack;

import static civ.State.UnitState.UnitSelected;

class CityView extends JPanel implements ActionListener{
	private JLabel name = new JLabel();
    private JLabel image = new JLabel("Mecca "); 
    private JLabel cityImg = new JLabel();
    private JLabel buildLabel = new JLabel();
    
    private JPanel imgPane = new JPanel();
    private JPanel leftPane = new JPanel();
    private JPanel centerPane = new JPanel();
    private JPanel rightPane = new JPanel();
    
    private JProgressBar unitProgressBar = new JProgressBar();
    private JButton recruitButton = new JButton("Visa rekrytmeny ");
    //private JButton buildButton = new JButton("Visa byggmeny ");
    //private JButton infoButton = new JButton("Visa info ");
    
    private ViewPort vp;
    private String unitName;
    private int cost;
    private City city;
    
    //private State state = new State();
    
    public CityView(City city){
    	setLayout(new BorderLayout());
    	
    	recruitButton.addActionListener(this);
    	//buildButton.addActionListener(this);
    	//infoButton.addActionListener(this);
    	
    	cityImg.setIcon(new ImageIcon(city.getImage()));
    	cityImg.setBorder(BorderFactory.createMatteBorder(6,6,6,6,Color.GREEN));
    	imgPane.add(cityImg);
		
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
        leftPane.add(imgPane);
        buildLabel.setText("Ingen unit skapas för närvarande");
        
        /*try{
        	buildLabel.setText("Just nu skapas: " + city.getBuildingUnit());
        	System.out.println(city.getBuildingUnit());
        	buildLabel.repaint();
        }
        catch(NullPointerException ne){
        	//new NullPointerException("Ingen enhet byggs atm");
        	System.out.println("Ingen enhet byggs");
        }*/
        
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.PAGE_AXIS));
        centerPane.add(Box.createRigidArea(new Dimension(0,5)));
        centerPane.add(recruitButton);
        centerPane.add(Box.createRigidArea(new Dimension(0,5)));
        centerPane.add(buildLabel);
        centerPane.add(Box.createRigidArea(new Dimension(0,5)));
        

        /*centerPane.add(buildButton);
        centerPane.add(Box.createRigidArea(new Dimension(0,5)));
        centerPane.add(infoButton);*/
        
        add(leftPane, BorderLayout.WEST);
        add(centerPane, BorderLayout.CENTER);
        add(rightPane, BorderLayout.EAST);
        
        //state.addObserver(this);
        
        this.city = city;
        update();   
    }
    
    public void update(){
    	if(unitName != null){
    		cost = city.getCost();
        	buildLabel.setText("Just nu skapas: " + unitName);
        	if(cost == 1)
        		unitProgressBar.setString("");
        	unitProgressBar.setValue(cost);
        	centerPane.repaint();
        }
        
        else {
        	System.out.println("NULL ingen setUnitBuilding");
        }
        
        /*else {
        	buildLabel.setText("Ingen unit skapas för närvarande");
        	buildLabel.repaint();
        }*/
    }
    
    public void setUnitBuilding(PhysicalUnitType type){
    	if(type != null){
    		unitName = type.getName();
    		unitProgressBar.setMaximum(type.getCost());
    		unitProgressBar.setString(type.getName());
    		unitProgressBar.setStringPainted(true);
    		centerPane.add(unitProgressBar);
    		update();
    	}
    	else{
    		System.out.println("Går inte!!");
    	}
    }
    
    public void setUnitBuilding(){
    	unitName = "Ingen enhet";
    	unitProgressBar.setValue(0);
    }
    
     public void actionPerformed(ActionEvent ae){
     	 if(ae.getSource() == recruitButton){	 
     	 	 ViewPort.getTabbed().setTab();
     	 	 ViewPort.getPopup().setVisible(true);
     	 	 
     	 	 System.out.println("Rekryt ");
     	 }
     	 /*else if (ae.getSource() == buildButton)
     	 	 System.out.println("Bygga ");
     	 else if (ae.getSource() == infoButton)
     	 	 System.out.println("Info ");*/
     }
}















