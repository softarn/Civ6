package civ;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class City {

    //private PhysicalUnit phu = new PhysicalUnit(null, null);
    private static GameMap gm = GameMap.getInstance();
    private String name;
    private Player owner;
    private Hold hold = new Hold();
    private CityView cityView;
   
    private PhysicalUnitType type;
    private PhysicalUnit pu;
    private Tile tile;
    private int cost = 0;
    
    private Image cityImg;
    private int defence;

    public City(String name, Player owner) {
        this.name = name;
        this.owner = owner;
		defence = 100;
        Round.addCity(this);
    	
        try {
            cityImg =  Toolkit.getDefaultToolkit().getImage((getClass().getResource("/data/img/city.png")));
        }
        catch (Exception ioE) {
            System.out.println("Bilden finns ej ");    
        }
        
        cityView = new CityView(this);
    }

    public void setName(String newName){
        name = newName;
    }

    public void setOwner(Player newOwner){
        owner = newOwner;
    }

    public void count(){
        if(this.cost == 1){
            //spawn unit here
            System.out.println("Spawning unit");
            pu = new PhysicalUnit(type, Round.getMe());
            if(GameServer.makeUnit(tile, type)){
                tile.getCity().getHold().addUnit(pu);
                tile.getView().repaint();
                int choice = JOptionPane.showConfirmDialog( null, "En ny enhet, " + type.getName() + ", har blivit skapad. Vill du gå till dess position?", "Enhet skapad", JOptionPane.YES_NO_OPTION);
                if(choice == 0){
                    tile.select();
                    gm.getView().centerOn(tile);
                    Menu.getInstance().getTabs().setSelectedComponent(pu.getView());
                }
                //Reset "now building" label
                getView().setUnitBuilding();
            }
        }
        if(cost >= 0){
            --cost;
        }
        
        cityView.update();
    }

    public void spawnCounter(PhysicalUnitType type, Tile tile, int cost){
        this.type = type;
        this.tile = tile;
        this.cost = cost;
    }
    
    /*public String getBuildingUnit(){
    	return pu.getType().getName();
    }*/
   
    public String getName(){
        return name;
    }

    public boolean isMine(){
        return Round.getMe().equals(owner);
    }

    public Hold getHold(){
        return hold;
    }
    
    public CityView getView(){
    	return cityView;	
    }
    
    public Image getImage() {
        return cityImg;
    }

    public int getDefence() {
    	return defence;	
    }
    
    public int getCost(){
    	return cost;
    }
}
