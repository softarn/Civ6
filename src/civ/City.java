package civ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class City {
    //private PhysicalUnit phu = new PhysicalUnit(null, null);
    public String name = new String("");
    private BufferedImage cityImg;
    
    public City() {
        //this.phu = phu;
        
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
    
}