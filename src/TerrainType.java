public class TerrainType {
	private String name;
	private int attackBonus;
	private int defenceBonus;
        private String tilefile;
        private String fogfile;

        private static TerrainType[] types = {
          new TerrainType("Sea",        50, 0),
          new TerrainType("Ocean",      30, 20),
          new TerrainType("Plains",     25, 0),
          new TerrainType("Grassland",  25, 0),
          new TerrainType("March",      0,  25),
          new TerrainType("Desert",     10, 0),
          new TerrainType("Tundra",     15, 0),
          new TerrainType("Rainforest", 15, 50),
          new TerrainType("Conifer",    25, 75),
          new TerrainType("Broadleaf",  25, 75), 
          new TerrainType("Hills",      25, 100), 
          new TerrainType("Mountain",   20, 200), 
        };

        public static final int SEA = 0;
        public static final int OCEAN = 1;
        public static final int PLAINS = 2;
        public static final int GRASSLAND = 3;
        public static final int MARCH = 4;
        public static final int DESERT = 5;
        public static final int TUNDRA = 6;
        public static final int RAINFOREST = 7;
        public static final int CONIFER = 8;
        public static final int BROADLEAF = 9;
        public static final int HILLS = 10;
        public static final int MOUNTAIN = 11;
	
	private TerrainType(String name, int att, int def){
		this.name = name;
		attackBonus = att;
		defenceBonus = def;
                tilefile = "../data/img/"+name + ".png";
                fogfile = "../data/img/"+name + "fog.png";
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
