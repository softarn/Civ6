import java.util.ArrayList;

class GameMap {
	private Tile currentTile = null;
	private Tile[][] tiles;

	GameMap(){
	}
	
	public Tile[] getNeighbours(Tile tile){
		return new Tile[0];
	}
	
	public int getDistance(Tile a, Tile b){
		return 1;
	}
	
	public void parseMap(String terrain){
	}
}