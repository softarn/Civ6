package src;

import java.util.ArrayList;
import java.util.Random;

import static src.State.UnitState.Selected;

public class GameMap{
    private static final State state = State.getInstance();
    private Tile currentTile = null;
    private Tile[][] tiles;
    private static GameMap map;
    private GameMapView gmv;
    private int height = 15, width = 15;

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
        /*
            kolumn =((pixelx - offsetX) / bredd)
            rad = ((pixely - offsetY) / (höjd/2))
            x = (kolumn + rad) / 2
            y = (rad - kolumn) / 2
        */
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
     * Sets all the tiles in the map.
     *
     * @param terrain Indata from the server interface.
     */
    public void parseMap(String terrain){
        TerrainType[] tt = TerrainType.values();
        Random r = new Random();
        Tile[][] result = new Tile[width][];
        for(int k=0; k<width; k++){
            result[k] = new Tile[height];
        }
        for(int j=height-1; j>=0; --j){
            for(int i=width-1; i>=0; --i){
                int rand = Math.abs(r.nextInt())%tt.length;
                Tile temp = new Tile(tt[rand], i, j);
                result[i][j] = temp;
            }
        }
        result[1][1].setUnit(new PhysicalUnit(PhysicalUnitType.Musketeer));
        tiles = result;
    }
}
