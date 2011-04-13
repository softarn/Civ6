package src;

import java.util.ArrayList;

public class GameMap{
    private Tile currentTile = null;
    private Tile[][] tiles = {
            {new Tile(TerrainType.Broadleaf, 0, 0), new Tile(TerrainType.Mountain, 0, 1), new Tile(TerrainType.Mountain, 0, 2)},
            {new Tile(TerrainType.Mountain, 1, 0), new Tile(TerrainType.Desert, new PhysicalUnit(PhysicalUnitType.Musketeer), 1, 1), new Tile(TerrainType.Mountain, 1, 2)},
            {new Tile(TerrainType.Mountain, 2, 0), new Tile(TerrainType.Desert, 2, 1), new Tile(TerrainType.Mountain, 2, 2)},
    };
    private static GameMap map;
    private GameMapView gmv;
    private int height = 3, width = 3;

    private int[][] offsets = {{-1,-1},
                            {0,-1},
                            {1,0},
                            {1,1},
                            {0,1},
                            {-1,0}};


    public GameMap(GameMapView gmv){
        super();

        // Parsing the map
        parseMap("Put server map data here");

        this.gmv = gmv;
        // Put all TileViews on the GameMapView
        for(int j=getHeight()-1; j>=0; --j){
            for(int i=getWidth()-1; i>=0; --i){
                gmv.add(tiles[i][j].getView());
            }
        }
        map = this;
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
        if(x > getWidth() || y > getHeight() ||
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
     * @param range Neighbours in this range will be returned
     */
    public ArrayList<Tile> getNeighbours(Tile tile, int range){
        int x = tile.getX();
        int y = tile.getY();

        ArrayList<Tile> acc = new ArrayList<Tile>();

        for(int[] off : offsets){
            Tile t = getTile(x - off[0], y - off[1]);
            if(t != null)
                acc.add(t);
        }

        return acc;
        //return getNeighbours(tile, --range, acc);
    }

    /**
     * Not finished yet!
     */
    private ArrayList<Tile> getNeighbours(Tile tile, int range, ArrayList<Tile> acc){
        if(range < 1){
            acc.remove(tile);
            return acc;
        }

        ArrayList<Tile> tmp = new ArrayList<Tile>();

        for(Tile t : acc){
            for(Tile t2 : getNeighbours(t,1)){
                if(!acc.contains(t2))
                    tmp.add(t2);
            }
        }

        acc.addAll(tmp);
        return getNeighbours(tile, --range, acc);            
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
