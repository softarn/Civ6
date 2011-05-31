package civ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static civ.TerrainType.*;

class MapGen{
    private static final int LAND = 45;
    private static final int WATER = 55;
    private static final int PLAINS_MODIFIER = 30;
    private static final int SEA_MODIFIER = 50;

    private static final TerrainType[] terrains = TerrainType.values();
    private static final int[][] offsets = {
        {-1,-1},
        {0,-1},
        {1,0},
        {1,1},
        {0,1},
        {-1,0}
    };

    private static Tile[][] tiles;
    private static Random r = new Random();
    private static int width, height;

    public static Tile[][] generate(int w, int h){
        width = w;
        height = h;

        tiles = new Tile[width][];
        for(int k=0; k<width; k++){
            tiles[k] = new Tile[height];
        }
        tiles[height-1][width-1] = new Tile(terrains[2], height - 1, width - 1);
        for(int j = height - 1; j >= 0; --j){
            for(int i = width - 1; i >= 0; --i){
                if(j == height - 1 && i == width - 1)
                    continue;

                tiles[i][j] = createTile(i, j);
            }
        }
        return tiles;
    }

    private static Tile createTile(int x, int y){
        int rand = Math.abs(r.nextInt());
        int index = 0;
        ArrayList<Tile> neighbours = getNeighbours(x, y);
        HashMap<TerrainType, Integer> occurance = new HashMap<TerrainType, Integer>();
        for(Tile neighbour : neighbours){
            if(occurance.containsKey(neighbour.getTerrain())){
                occurance.put(neighbour.getTerrain(), occurance.get(neighbour.getTerrain()) + 1);
            }
            else{
                occurance.put(neighbour.getTerrain(), 1);
            }
        }
        switch(getRandom()){
            case Sea:
                rand = Math.abs(r.nextInt());
                if(rand % 100 < WATER){
                    index = 0;
                }
                else{
                    index = 2;
                }
                break;
            default:
                rand = Math.abs(r.nextInt());
                if(rand % 100 < LAND){
                    index = 2;
                }
                else{
                    index = 0;
                }
                break;
        }
        return new Tile(terrains[index], x, y);
    }

    private static TerrainType getRandom(){
        return terrains[Math.abs(r.nextInt())%terrains.length];
    }

    private static TerrainType getMax(HashMap<TerrainType, Integer> map){
        int max = 0;
        TerrainType result = null;
        for(TerrainType terrain : map.keySet()){
            if(map.get(terrain) > max){
                result = terrain;
                max = map.get(terrain);
            }
        }
        return result;
    }

    private static Tile getTile(int x, int y){
        if(x >= width || y >= height || x < 0 || y < 0){
            return null;
        } 
        return tiles[x][y];
    }

    private static ArrayList<Tile> getNeighbours(int x, int y){
        ArrayList<Tile> result = new ArrayList<Tile>();
        Tile tile;
        for(int[] off : offsets){
            tile = getTile(x - off[0], y - off[1]);
            if(tile != null)
                result.add(tile);
        }
        return result;
    }
}
