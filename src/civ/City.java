package civ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class City {
<<<<<<< HEAD
    public String name = new String("");
=======
    //private PhysicalUnit phu = new PhysicalUnit(null, null);
    private String name = new String("");
    private Hold hold = new Hold();
>>>>>>> 982d2733b5d06acdb94ec763ed769d2231d49f6e
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
    
    public BufferedImage getImage() {
        return cityImg;
    }
    
<<<<<<< HEAD
    public int getDefence () {
    	return defence;	
    }
    
}
=======
}
>>>>>>> 982d2733b5d06acdb94ec763ed769d2231d49f6e
