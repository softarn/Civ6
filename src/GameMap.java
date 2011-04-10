import java.util.ArrayList;

public class GameMap{
  private Tile currentTile = null;
  private Tile[][] tiles;
  private GameMapView gmv;

  public GameMap(GameMapView gmv){
    super();

    // Parsing the map
    parseMap("Put server map data here");

    this.gmv = gmv;
    // Put all TileViews on the GameMapView
    for(int i=0; i<getWidth(); ++i){
      for(int j=0; j<getHeight(); ++j){
        gmv.add(tiles[i][j].getView());
      }
    }
  }

  /**
   * Get a specific tile.
   */
  public Tile getTile(int x, int y){
    return null;
  }

  /**
   * Get the width of the map.
   * Width is in the X direction.
   */
  public int getWidth(){
    return 0;
  }

  /**
   * Get the height of the map.
   * Height is in the Y direction.
   */
  public int getHeight(){
    return 0;
  }

  public Tile[] getNeighbours(Tile tile, int range){
    return new Tile[0];
  }

  public int getDistance(Tile a, Tile b){
    return 1;
  }

  /**
   * Adds all tiles to the map.
   *
   * @param terrain Indata from the server interface.
   */
  public void parseMap(String terrain){
    tiles = new Tile[0][0];
  }
}
