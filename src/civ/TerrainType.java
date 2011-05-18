package civ;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public enum TerrainType {
        Sea("Sea",                 50, 0,  0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 6, 0, 0),
        Ocean("Ocean",             30, 20, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 4, 0, 0),
        Plains("Plains",           25, 0,  1, 2, 2, 2, 1, 0, 0, 1, 1, 5, 0, 1, 6),
        Grassland("Grassland",     25, 0,  1, 2, 2, 2, 0, 0, 0, 1, 1, 6, 0, 1, 8),
        Marsh("Marsh",             0,  25, 1, 1, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0),
        Desert("Desert",           10, 0,  1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0),
        Tundra("Tundra",           15, 0,  1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1),
        Rainforest("Rainforest",   15, 50, 1, 1, 0, 0, 0, 0, 0, 0, 6, 1, 0, 4, 1),
        Conifer("Conifer",         25, 75, 1, 2, 1, 0, 0, 0, 0, 0, 10,1, 0, 5, 1),
        Broadleaf("Broadleaf",     25, 75, 1, 2, 1, 0, 0, 0, 0, 0, 8, 1, 0, 6, 1),         
        Hills("Hills",             25, 100,1, 0, 1, 1, 6, 8, 1, 4, 1, 1, 0, 1, 1), 
        Mountain("Mountain",       20, 200,1, 0, 0, 0, 7, 6, 6, 3, 0, 0, 0, 0, 0);
        
    /*
    private int [12] seaArray = {};
    private int [12] oceanArray = {};
    private int [12] plainsArray = {};
    private int [12] grasslandArray = {};
    private int [12] marshArray = {};
    private int [12] desertArray = {};
    private int [12] tundraArray = {};
    private int [12] rainforrestArray = {};
    private int [12] coniferArray = {};
    private int [12] broadleafArray = {};
    private int [12] hillsArray = {};
    private int [12] mountainArray = {};*/
    
    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private final String name;
    private final int attackBonus;
    private final int defenceBonus;
    private final String tilefile;
    private final String fogfile;
    // An array of travelrules
    private final String[][] traversible = {{"Boat"}, {"Artillery", "Range", "Melee", "Mounted", "Other"}};
    private BufferedImage normalImg;
    private BufferedImage fogImg;
    private ResourceType type;
    private ResourceUnit runit;

    ResourceUnit[] resources;
    private String[] traversibleFor;

    private TerrainType(String name, int att, int def, int travelRule, int gold, int happiness, int science, int stone, int ore, 
            int sulfur, int coal, int lumber, int wheat, int fish, int game, int hay) {
                
        this.name = name;
        attackBonus = att;
        defenceBonus = def;
        tilefile = name + ".png";
        fogfile = name + "Fog.png";
                
        resources = new ResourceUnit[]{
            new ResourceUnit(ResourceType.Gold, gold),
            new ResourceUnit(ResourceType.Happiness, happiness),
            new ResourceUnit(ResourceType.Science, science),
            new ResourceUnit(ResourceType.Stone, stone),
            new ResourceUnit(ResourceType.Ore, ore),
            new ResourceUnit(ResourceType.Sulfur, sulfur),
            new ResourceUnit(ResourceType.Coal, coal),
            new ResourceUnit(ResourceType.Lumber, lumber),
            new ResourceUnit(ResourceType.Wheat, wheat),
            new ResourceUnit(ResourceType.Fish, fish),
            new ResourceUnit(ResourceType.Game, game),
            new ResourceUnit(ResourceType.Hay, hay)
        };
            
        traversibleFor = traversible[travelRule];
        
        try{
            normalImg = ImageIO.read(new File(imgPath + tilefile));
            fogImg = ImageIO.read(new File(imgPath + fogfile));
        } catch(IOException e){
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

    public boolean isTraversible(PhysicalUnit unit){
        return isTraversible(unit.getType());
    }

    public boolean isTraversible(AbstractUnitType type){
        for(String category : traversibleFor){
            if(type.getCategory().equals(category)){
                return true;
            }
        }
        return false;
    }

	public int getAttackBonus() {
		return attackBonus;
	}

	public int getDefenceBonus() {
		return attackBonus;
	}
    
    public ArrayList<ResourceUnit> getResources(){ 
        ArrayList<ResourceUnit> arrayValues = new ArrayList<ResourceUnit> ();
        for(ResourceUnit ru : resources) {
            if (ru.getAmount() == (0))
                continue;
            
            else { 
                arrayValues.add(ru);
            }
        }
        return arrayValues;
    }
    
    
    
    public ResourceUnit getResourceUnit() {
        return runit;
    }

}
