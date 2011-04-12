import java.util.ArrayList;

public class GameMap{
    private Tile currentTile = null;
    private Tile[][] tiles = {
            {new Tile(TerrainType.Mountain, 0, 0), new Tile(TerrainType.Mountain, 0, 1), new Tile(TerrainType.Mountain, 0, 2)},
            {new Tile(TerrainType.Mountain, 1, 0), new Tile(TerrainType.Desert, new PhysicalUnit(PhysicalUnitType.Musketeer), 1, 1), new Tile(TerrainType.Mountain, 1, 2)},
            {new Tile(TerrainType.Mountain, 2, 0), new Tile(TerrainType.Desert, 2, 1), new Tile(TerrainType.Mountain, 2, 2)},
    };
    private GameMapView gmv;
    private int height = 3, width = 3;

    public GameMap(GameMapView gmv){
        super();

        // Parsing the map
        parseMap("Put server map data here");

        this.gmv = gmv;
        // Put all TileViews on the GameMapView
        for(int j=getHeight()-1; j>=0; --j){
            for(int i=0; i<getWidth(); ++i){
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
        return width;
    }

    /**
     * Get the height of the map.
     * Height is in the Y direction.
     */
    public int getHeight(){
        return height;
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
    /*    tiles = {
            {new Tile(null, 0, false, 0, 0), new Tile(null, 0, false, 0 , 1)},
            {new Tile(null, 0, true, 1, 0), new Tile(null, 0, false, 1, 1)},
            {new Tile(null, 0, true, 2, 0), new Tile(null, 0, false, 2, 1)}
        };
    */
    }
}
