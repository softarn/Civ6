package civ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class City {

    //private PhysicalUnit phu = new PhysicalUnit(null, null);
    private String name;
    private Player owner;
    private Hold hold = new Hold();
    private CityView cityView;
    
    private BufferedImage cityImg;
    private int defence;
    
    public City(String name, Player owner) {
        this.name = name;
        this.owner = owner;
    	defence = 100;
    	
        try {
            cityImg =  ImageIO.read(new File("data/img/city.png"));
        }
        catch (IOException ioE) {
            System.out.println("Bilden finns ej ");    
        }
        
        cityView = new CityView(this);
    	
    }

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
    
    public BufferedImage getImage() {
        return cityImg;
    }

    public int getDefence () {
    	return defence;	
    }
}
