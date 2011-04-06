public class Tile {
	private boolean explored, fog, plain;
	private int countToFog;
	private int x, y;
	
	private TerrainType terrain;
	
	Tile(TerrainType tt, int countToFog, boolean plain, int x, int y){
		explored = false;
		fog = true;
		countToFog = 0;
		
		terrain = tt;
		this.plain = plain;
		this.x = x;
		this.y = y;		
	}
}