package civ;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum TerrainType {
    Sea("Sea",     50, 0),
        Ocean("Ocean",      30, 20),
        Plains("Plains",     25, 0),
        Grassland("Grassland",  25, 0),
        Marsh("Marsh",      0,  25),
        Tundra("Tundra",     15, 0),
        Rainforest("Rainforest", 15, 50),
        Conifer("Conifer",    25, 75),
        Hills("Hills",      25, 100), 
        Broadleaf("Broadleaf",  25, 75), 
        Desert("Desert",     10, 0),
        Mountain("Mountain",   20, 200);

    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private final String name;
    private final int attackBonus;
    private final int defenceBonus;
    private final String tilefile;
    private final String fogfile;
    private BufferedImage normalImg;
    private BufferedImage fogImg;

    private TerrainType(String name, int att, int def){
        this.name = name;
        attackBonus = att;
        defenceBonus = def;
        tilefile = name + ".png";
        fogfile = name + "Fog.png";

        try{
            normalImg = ImageIO.read(new File(imgPath + tilefile));
            fogImg = ImageIO.read(new File(imgPath + fogfile));
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public BufferedImage getNormalImage(){
        return normalImg;
    }

    public BufferedImage getFogImage(){
        return fogImg;
    }
    
    public String getName() {
	return name;
    }

    /**
     * Returns the cost to pass this terrain for the specific unittype.
     * The range is a scale from 1-10 where 10 is impassable.
     */
    public int getRangeCost(PhysicalUnitType unit){
        return 1;
    }

	public int getAttackBonus() {
		return attackBonus;
	}

	public int getDefenceBonus() {
		return attackBonus;
	}

}