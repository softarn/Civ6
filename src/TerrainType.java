public enum TerrainType {
    Sea("Sea",     50, 0),
        Ocean("Ocean",      30, 20),
        Plains("Plains",     25, 0),
        Grassland("Grassland",  25, 0),
        March("March",      0,  25),
        Desert("Desert",     10, 0),
        Tundra("Tundra",     15, 0),
        Rainforest("Rainforest", 15, 50),
        Conifer("Conifer",    25, 75),
        Broadleaf("Broadleaf",  25, 75), 
        Hills("Hills",      25, 100), 
        Mountain("Mountain",   20, 200);

    private final String name;
    private final int attackBonus;
    private final int defenceBonus;
    private final String tilefile;
    private final String fogfile;

    private TerrainType(String name, int att, int def){
        this.name = name;
        attackBonus = att;
        defenceBonus = def;
        tilefile = name + ".png";
        fogfile = name + "fog.png";
    }

    /**
     * Get a terraintype by id.
     * 
     * @param id A terrain as defined by the constants.
     *
     * @return An already created terraintype.
     */
    public static TerrainType getInstance(int id){
        if(id >= 0 && id < 12){
            return types[id];
        }
        else{
            System.out.println("Undefined terraintype id: " + id);
            return null;
        }
    }

    public String getTileImage(){
        return tilefile;
    }

    public String getFogImage(){
        return fogfile;
    }

}
