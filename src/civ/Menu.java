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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
    private JPanel createUnit = new JPanel(); // Temporary panel

    private JTabbedPane tabbedPane;
    private GlobalView globalViewObject;
    
    private PhysicalUnitView unitView;	
    private CityView cityView;
    private JProgressBar manPowerBar;    
    private JLabel tileLabel;
    //private JLabel unitPresentation;
    private GameMapView gmv = new GameMapView();    

    private JButton plus;
    private JButton minus;

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getUnitState());
    //private PopupWindow puw;
    private int curScale = 5 ;
    private int[] sizes = {35, 50, 75, 90, 120, 150, 175, 190};

    private static final String imgPath = "data/img/"; //Need a better fix for this!
    private BufferedImage img;
    
    Menu(){ 
        super(); 
        setLayout(new BorderLayout(0,10)); 
        
        String name = "medievalwallpaper";
        try{
            img = ImageIO.read(new File(imgPath + name + ".jpg"));
        }catch(IOException e){
            System.out.println(e);
            System.out.println("name");
        }
        //this.puw = puw;
        //Popup popup;
        //popup = PopupFactory.getSharedInstance().getPopup(null, puw, 200,200);
		
        
        manPowerBar = new JProgressBar(0,100);  
        manPowerBar.setSize(new Dimension(30,10));
        manPowerBar.setString("Manpower "); 
        manPowerBar.setStringPainted(true); 
        
        //unitPresentation = new JLabel();
        tileLabel = new JLabel();
        tileLabel.setOpaque(false);
 
        plus = new JButton("+");
        plus.addActionListener(this);
        minus = new JButton("-");
        minus.addActionListener(this);

        createUnit.add(plus);
        createUnit.add(minus);
        createUnit.setOpaque(false);        

        tabbedPane = new JTabbedPane(); 
       
        add(north, BorderLayout.NORTH);      
        add(westPanel,  BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(createUnit, BorderLayout.CENTER);
        
        // Set layout managers 
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS)); 
        //north.setOpaque(false);
        eastPanel.setLayout(new BorderLayout());
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension (600,220));
        westPanel.setOpaque(false);
        
        // ADD CONTENT
        globalViewObject = new GlobalView();
        
        tabbedPane.addTab(" Hey ", null, unitView, "Inget objekt markerat ");
        tabbedPane.setPreferredSize(new Dimension(580,200));
            
        // East
        eastPanel.add(globalViewObject, BorderLayout.EAST);
        
        // West
        westPanel.add(tabbedPane, BorderLayout.WEST);
        
        north.add(tileLabel);
        //north.add(unitPresentation);
        
        state.addObserver(this);
        tabbedPane.repaint();
        update(); 
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

    public void update(Observable obs, Object obj){
        if(obs == state){
            update();
        }
    }
    
    private void update(){
        switch (state.getHoverState()) {
            case HoverNone:
                tileLabel.setText("<html>No tile" + "<br />&nbsp;</html>");
                break;
            case HoverTileOnly:
                tileLabel.setText("<html>Terrain: \n" + state.getHoverTile().getTerrain().toString() + "<br />&nbsp;</html>");
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
                    Player owner = state.getHoverTile().getUnit().getOwner();
                    if(owner != null){
                        tileLabel.setText("<html>Terrain: " + outputTerrain +
                                "<br>Unit: Enemy unit, belongs to "+owner);
                    }
                    else{
                        tileLabel.setText("<html>Terrain: " + outputTerrain +
                                "<br>Unit: Enemy unit</html>");
                    }

                }
                
                break;
        }        

        switch(state.getUnitState()){
            case UnitSelected: 
                if(state.getSelectedUnit().isAlly()){
                    PhysicalUnit unit = state.getSelectedUnit();
                 	String unitTypeName = unit.getType().getName();
                 	tabbedPane.addTab(unitTypeName, null, unit.getView(), "Visa dina units ");
                }
                break;
            case UnitUnSelected:                 
                tabbedPane.removeAll();
                tabbedPane.repaint();
                break;
        }
        
        switch (state.getCityState()) {
        	case CitySelected:
        		City city = state.getSelectedCity();
        		String cityName = city.getName();
        		tabbedPane.addTab(cityName, null, city.getView(), "Visa dina städer ");
        			
        		System.out.println(city.getHold().getUnits());
        		
        		for (PhysicalUnit pu : city.getHold().getUnits()) {
        			tabbedPane.addTab(pu.getType().getName(), null, pu.getView(), "Visa ---" );	
        			//tabbedPane.add();
        			System.out.println("Dirve");
        		}
        		
        		
        		break;
        	case CityUnSelected:
        		tabbedPane.repaint();
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
}

