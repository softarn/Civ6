package src;

import java.util.ArrayList;

import static src.State.UnitState.Selected;

public class GameMap{
    private static final State state = State.getInstance();
    private Tile currentTile = null;
    private Tile[][] tiles;
    private static GameMap map;
    private GameMapView gmv;
    private int height = 3, width = 7;

    private int[][] offsets = {{-1,-1},
                            {0,-1},
                            {1,0},
                            {1,1},
                            {0,1},
                            {-1,0}};


    public GameMap(GameMapView gmv){
        super();
        map = this;

        // Parsing the map
        parseMap("Put server map data here");
        for(Tile[] temp : tiles){
            for(Tile tile : temp){
                if(tile.hasUnit()){ // Check if this unit is owned by the player too
                    for(Tile t : getNeighbours(tile, 1)){
                        t.setExplored(true);
                    }
                }
            }
        }
        this.gmv = gmv;
        // Put all TileViews on the GameMapView
        for(int j=getHeight()-1; j>=0; --j){
            for(int i=getWidth()-1; i>=0; --i){
                gmv.add(tiles[i][j].getView());
            }
        }
    }

    /**
     * Temporary solution to be able to reach the map from anywhere.
     * Assumes GameMapView initialized the object before this is called.
     */
    public static GameMap getInstance(){
        return map;
    }

    /**
     * Get a specific tile from the array.
     */
    public Tile getTile(int x, int y){
        if(x >= getWidth() || y >= getHeight() ||
                x < 0 || y < 0){
            return null;
        } 
        return tiles[x][y];
    }

    /**
     * Get a specific tile from the array at the 
     * coordinates relative to GameMapView.
     */
    public Tile getTileAt(int x, int y){
        for(int j=getHeight()-1; j>=0; --j){
            for(int i=getWidth()-1; i>=0; --i){
                if(tiles[i][j].getView().contains(x, y)){
                    return tiles[i][j];
                }
            }
        }
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

    /**
     * Get all neighbours to a tile in an ArrayList(Not finished yet!)
     * @param tile Neighbours to this tile will be returned
     * @param range Neighbours in this range will be returned.
     */
    public ArrayList<Tile> getNeighbours(Tile tile, int range){
        ArrayList<Tile> result = new ArrayList<Tile>();
        for(int[] t : tile.getNeighbours(range)){
            Tile temp = getTile(t[0], t[1]);
            if(temp != null){
                result.add(temp);
            }
        }
        return result;
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
        Tile[][] temp = {
            {new Tile(TerrainType.Mountain, new PhysicalUnit(PhysicalUnitType.Musketeer), 0, 0), new Tile(TerrainType.Mountain, 0, 1), new Tile(TerrainType.Mountain, 0, 2)},
            {new Tile(TerrainType.Mountain, 1, 0), new Tile(TerrainType.Desert, new PhysicalUnit(PhysicalUnitType.Musketeer), 1, 1), new Tile(TerrainType.Mountain, 1, 2)},
            {new Tile(TerrainType.Mountain, 2, 0), new Tile(TerrainType.Desert, 2, 1), new Tile(TerrainType.Sea, 2, 2)},
            {new Tile(TerrainType.Marsh, 3, 0), new Tile(TerrainType.Plains, 3, 1), new Tile(TerrainType.Hills, 3, 2)},
            {new Tile(TerrainType.Marsh, 4, 0), new Tile(TerrainType.Plains, 4, 1), new Tile(TerrainType.Plains, 4, 2)},
            {new Tile(TerrainType.Hills, 5, 0), new Tile(TerrainType.Desert, 5, 1), new Tile(TerrainType.Grassland, 5, 2)},
            {new Tile(TerrainType.Broadleaf, 6, 0), new Tile(TerrainType.Desert, 6, 1), new Tile(TerrainType.Marsh, 6, 2)},
        };
        tiles = temp;
    }
}
