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
import javax.swing.BorderFactory;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import java.util.Observer;
import java.util.Observable;
import java.io.File;
import java.io.IOException;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.Box;

import javax.imageio.ImageIO;

import java.awt.*;
import javax.imageio.ImageIO;

import java.awt.*;

import static civ.State.TileState.TileSelected;
import static civ.State.TileState.TileUnSelected;
import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;
import static civ.State.ActionState.None;
import static civ.State.ActionState.Move;
import static civ.State.ActionState.Attack;

import static civ.State.CityState.CitySelected;
import static civ.State.CityState.CityUnSelected;
import static civ.State.HoverState.HoverNone;
import static civ.State.HoverState.HoverTileOnly;
import static civ.State.HoverState.HoverTileUnit;
import static civ.State.HoverState.HoverTileCity;

public class Menu extends JPanel implements Observer, ActionListener, ChangeListener{

    private static final State state = State.getInstance();
    private JPanel north = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel createUnit = new JPanel(); // Temporary panel

    private JTabbedPane tabbedPane;
    private GlobalView globalViewObject;

    private PhysicalUnitView unitView;	
    private CityView cityView;
    private JProgressBar manPowerBar;    
    private JLabel tileLabel;
    //private JLabel unitPresentation;
    private GameMapView gmv = new GameMapView();    

    private JLabel zoomLabel;
    private JButton plus;
    private JButton minus;

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getUnitState());
    //private PopupWindow puw;
    private int curScale = 5;
    private int index = 0;
    private City oldCity = null;
    private PhysicalUnit oldUnit = null;
    private int[] sizes = {35, 50, 75, 90, 120, 150, 175, 190};

    private static final String imgPath = "/data/img/"; //Need a better fix for this!
    private Image img;
    private static Menu menu = new Menu();

    private Menu(){ 
        super(); 
        setLayout(new BorderLayout(0,10)); 

        String name = "medievalwallpaper";
        try{
            img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imgPath + name + ".jpg"));
        } catch(Exception e){
            System.out.println(e);
            System.out.println(name);
        }
        
        //this.puw = puw;
        //Popup popup;
        //popup = PopupFactory.getSharedInstance().getPopup(null, puw, 200,200);
        manPowerBar = new JProgressBar(0,100);  
        manPowerBar.setSize(new Dimension(30,10));
        manPowerBar.setString("Mankraft "); 
        manPowerBar.setStringPainted(true); 

        //unitPresentation = new JLabel();
        tileLabel = new JLabel();
        tileLabel.setOpaque(false);

        zoomLabel = new JLabel("Zoom");
        plus = new JButton("+");
        plus.addActionListener(this);
        minus = new JButton("-");
        minus.addActionListener(this);
        
        createUnit.setLayout(new BoxLayout(createUnit, BoxLayout.Y_AXIS));
        createUnit.add(Box.createRigidArea(new Dimension(170,20)));
        createUnit.add(zoomLabel);
        createUnit.add(Box.createRigidArea(new Dimension(5,5)));
        createUnit.add(plus);
        createUnit.add(Box.createRigidArea(new Dimension(5,5)));
        createUnit.add(minus);
        createUnit.add(Box.createRigidArea(new Dimension(5,5)));
        createUnit.setOpaque(false);        

        tabbedPane = new JTabbedPane(); 

        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS)); 
        north.add(tileLabel);

        // ADD CONTENT
        globalViewObject = new GlobalView();

        //tabbedPane.addTab(" TestLabel ", null, unitView, "Inget objekt markerat ");
        tabbedPane.setPreferredSize(new Dimension(580,200));
        tabbedPane.addChangeListener(this);

        // East
        //eastPanel.setLayout(new BorderLayout());
        //eastPanel.add(globalViewObject, BorderLayout.EAST);
        //eastPanel.setOpaque(false);

        // West
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension (600,220));
        westPanel.setOpaque(false);
        westPanel.add(tabbedPane, BorderLayout.WEST);

        add(north, BorderLayout.NORTH);
        add(westPanel,  BorderLayout.WEST);
        //add(eastPanel, BorderLayout.EAST);
        add(globalViewObject, BorderLayout.EAST);
        add(createUnit, BorderLayout.CENTER);

        state.addObserver(this);
        tabbedPane.repaint();
        update(); 
    }

    public static Menu getInstance(){
        return menu;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
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

    public JTabbedPane getTabs(){
        return tabbedPane;
    }

    public void update(Observable obs, Object obj){
        if(obs == state){
            update();
        }
    }

    private void update(){
        switch (state.getHoverState()){
            case HoverNone:
                tileLabel.setText("<html>No tile" + "<br />&nbsp;</html>");
                break;
            case HoverTileOnly:
                tileLabel.setText("<html>Terräng: \n" + state.getHoverTile().getTerrain().toString() + "<br />&nbsp;</html>");
                break;
            case HoverTileUnit:
                String outputTerrain = state.getHoverTile().getTerrain().toString();
                String outputUnit = Integer.toString(state.getHoverTile().getUnit().getManPower());
                if(state.getHoverTile().getUnit().isAlly()){
                    tileLabel.setText("<html>Terrain: " + outputTerrain +
                            "<br>Unit: " + state.getHoverTile().getUnit().getType().getName() +
                            " Anfall: " + state.getHoverTile().getUnit().getType().getAttack() + 
                            " Försvar: " + state.getHoverTile().getUnit().getType().getDefence() + 
                            " Manpower: " + outputUnit + "</html>");
                }
                else{
                    if (state.getHoverTile().hasFog() == true){
                    		System.out.println("Enemy unit in fog not visible");
                    		tileLabel.setText("");
                    	}
                    	
                        else {
                    	tileLabel.setText("<html>Terräng: " + outputTerrain +
                                "<br>Unit: Enemy unit, belongs to "+state.getHoverTile().getUnit().getOwner());
                        }            
                 	}
                break;
            case HoverTileCity:
                outputTerrain = state.getHoverTile().getTerrain().toString();
                City city = state.getHoverTile().getCity();
                if(state.getHoverTile().getCity().isMine()){
                    tileLabel.setText("<html>Terrain: " + outputTerrain +
                            "<br>City: " + city.getName());
                }
                else{
                    // Enemy unit hovered
                    Player owner = city.getOwner();
                    if(owner != null){
                        tileLabel.setText("<html>Terräng: " + outputTerrain +
                                "<br>City: " + city.getName() + "; Enemy city, belongs to "+owner);
                    }
                }
                break;
        }        

        switch(state.getUnitState()){
            case UnitSelected: 
                PhysicalUnit unit = state.getSelectedUnit();
                if(unit.isAlly()){
                    String unitTypeName = unit.getType().getName();
                    if(unit != oldUnit){
                        tabbedPane.removeAll();
                        tabbedPane.addTab(unitTypeName, null, unit.getView(), "Visa dina units ");
                        if(unit.getHold() != null){
                            for(PhysicalUnit pu : unit.getHold().getUnits()) {
                                tabbedPane.addTab(pu.getType().getName(), null, pu.getView(), "Visa ---" );	
                            }
                        }
                        tabbedPane.repaint();
                    }
                }
                break;
            case UnitUnSelected:
                /*if(index > -1 && tabbedPane.getTabCount() != 0 && index < tabbedPane.getTabCount()){
                  index = tabbedPane.getSelectedIndex();
                  }*/
                if(state.getCityState() == CityUnSelected){
                    oldCity = null;
                    tabbedPane.removeAll();
                    tabbedPane.repaint();
                }
                break;
        }

        switch (state.getCityState()) {
            case CitySelected:
                City city = state.getSelectedCity();
                if(city.isMine()){
                    String cityName = city.getName();
                    if(city != oldCity){
                        tabbedPane.removeAll();
                        tabbedPane.addTab(cityName, null, city.getView(), "Visa dina städer ");
                        for (PhysicalUnit pu : city.getHold().getUnits()) {
                            tabbedPane.addTab(pu.getType().getName(), null, pu.getView(), "Visa ---" );	
                        }
                        oldCity = city;
                        tabbedPane.repaint();
                    }
                }
                /*
                   System.out.println(index);
                   if(index > -1 && index <= tabbedPane.getTabCount()){
                   tabbedPane.setSelectedIndex(index);
                   state.setSelectedUnit(city.getHold().getUnits().get(index-1));
                   if(index <= 0) city.getHold().selUnitIndex(-1);
                   else city.getHold().selUnitIndex(index-1);
                   }*/
                break;
            case CityUnSelected:
                if(state.getUnitState() == UnitUnSelected){
                    oldUnit = null;
                    tabbedPane.removeAll();
                    tabbedPane.repaint();
                }
                break;
        }

        updateState();
    }

    private void updateState(){
        //tileLabel.setText("Status is: " + state.getUnitState());
    }

    public void actionPerformed(ActionEvent ae){
        if(plus == ae.getSource()){
            scaleUp();
        }
        else if(minus == ae.getSource()){
            scaleDown();
        }
    }

    public void stateChanged(ChangeEvent ce){
        if(tabbedPane == ce.getSource()){
            if(state.getCityState() == CitySelected){
                if(index > -1 && tabbedPane.getTabCount() != 0 && index < tabbedPane.getTabCount()){
                    //state.setCityState(CitySelected);
                    //update();
                }
            }
        }
    }
}

