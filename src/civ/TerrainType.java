package civ;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum TerrainType {
    Sea("Sea",     50, 0, {
        new ResourceUnit(ResourceType.Fish, 6), 
        new ResourceUnit(ResourceType.Gold, 2)
        new ResourceUnit(ResourceType.Happiness, 2)
        new ResourceUnit(ResourceType.Science, 2)}),
    
        Ocean("Ocean",      30, 20,{
            new ResourceUnit(ResourceType.Fish, 4), 
            new ResourceUnit(ResourceType.Gold, 1)
            new ResourceUnit(ResourceType.Happiness, 1)
            new ResourceUnit(ResourceType.Science, 1)}),
      
        Plains("Plains",     25, 0, {
            new ResourceUnit(ResourceType.Wheat, 5), 
            new ResourceUnit(ResourceType.Game, 1), 
            new ResourceUnit(ResourceType.Hay, 6), 
            new ResourceUnit(ResourceType.Stone, 1), 
            new ResourceUnit(ResourceType.Lumber, 1), 
            new ResourceUnit(ResourceType.Gold, 2), 
            new ResourceUnit(ResourceType.Coal, 1)
            new ResourceUnit(ResourceType.Happiness, 2)
            new ResourceUnit(ResourceType.Science, 2)}),
      
        Grassland("Grassland",  25, 0, {
            new ResourceUnit(ResourceType.Wheat, 6), 
            new ResourceUnit(ResourceType.Game, 1), 
            new ResourceUnit(ResourceType.hay, 8), 
            new ResourceUnit(ResourceType.Lumber, 1), 
            new ResourceUnit(ResourceType.Gold, 2), 
            new ResourceUnit(ResourceType.Coal, 1)
            new ResourceUnit(ResourceType.Happiness, 2)
            new ResourceUnit(ResourceType.Science, 2)}),
      
        Marsh("Marsh",      0,  25, {
            new ResourceUnit(ResourceType.Wheat, 2),
            new ResourceUnit(ResourceType.Hay, 2),
            new ResourceUnit(ResourceType.Ore, 2), 
            new ResourceUnit(ResourceType.Gold, 1)
            new ResourceUnit(ResourceType.Happiness, 0)
            new ResourceUnit(ResourceType.Science, 0)}),
        
        Desert("Desert",     10, 0, {
            new ResourceUnit(ResourceType.Wheat, 1), 
            new ResourceUnit(ResourceType.Gold, 1),
            new ResourceUnit(ResourceType.Ore, 1)
            new ResourceUnit(ResourceType.Happiness, 0)
            new ResourceUnit(ResourceType.Science, 1)}),
      
        Tundra("Tundra",     15, 0, {
            new ResourceUnit(ResourceType.Wheat, 1), 
            new ResourceUnit(ResourceType.Game, 1), 
            new ResourceUnit(ResourceType.hay, 1), 
            new ResourceUnit(ResourceType.Gold, 1)
            new ResourceUnit(ResourceType.Happiness, 0)
            new ResourceUnit(ResourceType.Science, 1)}),
       
        Rainforest("Rainforest", 15, 50, {
            new ResourceUnit(ResourceType.Wheat, 1), 
            new ResourceUnit(ResourceType.Game, 4),
            new ResourceUnit(ResourceType.Hay, 1), 
            new ResourceUnit(ResourceType.Lumber, 6),
            new ResourceUnit(ResourceType.Gold, 1)
            new ResourceUnit(ResourceType.Happiness, 0)
            new ResourceUnit(ResourceType.Science, 0)}),
       
        Conifer("Conifer",    25, 75, {
            new ResourceUnit(ResourceType.Wheat, 1), 
            new ResourceUnit(ResourceType.Game, 5), 
            new ResourceUnit(ResourceType.Hay, 1),
            new ResourceUnit(ResourceType.Lumber, 10), 
            new ResourceUnit(ResourceType.Gold, 2)
            new ResourceUnit(ResourceType.Happiness, 1)
            new ResourceUnit(ResourceType.Science, 0)}),
        
        Broadleaf("Broadleaf",  25, 75, {
            new ResourceUnit(ResourceType.Hay, 1),
            new ResourceUnit(ResourceType.Wheat, 1), 
            new ResourceUnit(ResourceType.Game, 6), 
            new ResourceUnit(ResourceType.Lumber, 8),
            new ResourceUnit(ResourceType.Gold, 2)
            new ResourceUnit(ResourceType.Happiness, 1)
            new ResourceUnit(ResourceType.Science, 0)}),         
            
        Hills("Hills",      25, 100, {
            new ResourceUnit(ResourceType.Wheat, 1), 
            new ResourceUnit(ResourceType.Game, 1), 
            new ResourceUnit(ResourceType.Stone, 6),
            new ResourceUnit(ResourceType.Hay, 1),
            new ResourceUnit(ResourceType.Ore, 8), 
            new ResourceUnit(ResourceType.Lumber, 1),
            new ResourceUnit(ResourceType.Coal, 4), 
            new ResourceUnit(ResourceType.Sulfur, 1)
            new ResourceUnit(ResourceType.Happiness, 1)
            new ResourceUnit(ResourceType.Science, 1)}), 
            
        Mountain("Mountain",   20, 200, {
            new ResourceUnit(ResourceType.Stone, 7), 
            new ResourceUnit(ResourceType.Ore, 6), 
            new ResourceUnit(ResourceType.Coal, 3), 
            new ResourceUnit(ResourceType.Sulfur , 6)
            new ResourceUnit(ResourceType.Happiness, 0)
            new ResourceUnit(ResourceType.Science, 0)});

            
    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private final String name;
    private final int attackBonus;
    private final int defenceBonus;
    private final String tilefile;
    private final String fogfile;
    private BufferedImage normalImg;
    private BufferedImage fogImg;
    private ResourceType type;
    private ResourceUnit runit;
    
    private HashMap<TerrainType, Integer> hm = new HashMap<TerrainType, Integer>();   
    
    
    private TerrainType(String name, int att, int def, ResourceUnit[] resources){
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
    
    public String getResources(){
        return ;
    }
    
    public ResourceUnit getResourceUnit() {
        return runit;
    }

}
