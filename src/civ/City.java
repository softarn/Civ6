package civ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class City {
    public String name = new String("");
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
    
    public BufferedImage getImage() {
        return cityImg;
    }
    
    public int getDefence () {
    	return defence;	
    }
    
}