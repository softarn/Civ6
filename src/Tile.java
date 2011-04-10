public class Tile {
	private boolean explored, fog, plain;
	private int countToFog;
	private int x, y;
	
	private TerrainType terrain;
	private PhysicalUnit unit;
        private TileView view;
	
        public Tile(){
          //Temporary empty tile constructor, remove this when it is not needed anymore.
        }
	public Tile(TerrainType tt, int countToFog, boolean plain, int x, int y){
		explored = false;
		fog = true;
		countToFog = 0;
		
		terrain = tt;
		this.plain = plain;
		this.x = x;
		this.y = y;
                view = new TileView(x*240, y*240, this);
	}
	
	public void setUnit(PhysicalUnit pu){
		unit = pu;
	}
	
	public PhysicalUnit getUnit(){
		return unit;
	}

        public TileView getView(){
                return view;
        }

        public TerrainType getTerrain(){
                return terrain;
        }

        public boolean isExplored(){
          return explored;
        }

        public boolean hasFog(){
          return fog;
        }
}
