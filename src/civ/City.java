package civ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class City {

    //private PhysicalUnit phu = new PhysicalUnit(null, null);
    private String name = new String("Mecca ");
    private Hold hold = new Hold();
    private CityView cityView = new CityView();
    
    private BufferedImage cityImg;
    private int defence;
    
    public City() {
    	defence = 100;
    	
        try {
            cityImg =  ImageIO.read(new File("data/img/city.png"));
        }
        catch (IOException ioE) {
            System.out.println("Bilden finns ej ");    
        }
    }

    public String getName(){
        return name;
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
