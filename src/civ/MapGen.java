package civ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static civ.TerrainType.*;

class MapGen{
    private static final int LAND_MOD = 65;
    private static final int WATER_MOD = 45;
    private static final int GRASSLAND_MOD = 10;
    private static final int MARSH_MOD = 10;
    private static final int DESERT_MOD = 10;
    private static final int TUNDRA_MOD = 10;
    private static final int RAINFOREST_MOD = 10;
    private static final int CONIFER_MOD = 10;
    private static final int BROADLEAF_MOD = 10;
    private static final int HILLS_MOD = 10;
    private static final int MOUNTAIN_MOD = 10;
    private static final int OCEAN_MOD = 50;

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
                tiles[i][j] = modifyTile(i, j);
            }
        }
        return tiles;
    }

    private static Tile modifyTile(int x, int y){
        int index = 0;
        int rand = Math.abs(r.nextInt());
        Tile tile = getTile(x, y);
        TerrainType terrain = tile.getTerrain();
        HashMap<TerrainType, Integer> occurance = new HashMap<TerrainType, Integer>();
        ArrayList<Tile> neighbours = getNeighbours(x, y);
        for(Tile neighbour : neighbours){
            if(occurance.containsKey(neighbour.getTerrain())){
                occurance.put(neighbour.getTerrain(), occurance.get(neighbour.getTerrain()) + 1);
            }
            else{
                occurance.put(neighbour.getTerrain(), 1);
            }
        }
        if(terrain == Plains){
            rand = Math.abs(r.nextInt());
            if(rand % 100 < GRASSLAND_MOD || getRandom(occurance) == Grassland)
                index = 3;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < MARSH_MOD || getRandom(occurance) == Marsh)
                index = 4;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < DESERT_MOD || getRandom(occurance) == Desert)
                index = 5;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < TUNDRA_MOD || getRandom(occurance) == Tundra)
                index = 6;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < RAINFOREST_MOD || getRandom(occurance) == Rainforest)
                index = 7;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < CONIFER_MOD || getRandom(occurance) == Conifer)
                index = 8;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < BROADLEAF_MOD || getRandom(occurance) == Broadleaf)
                index = 9;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < HILLS_MOD || getRandom(occurance) == Hills)
                index = 10;
            rand = Math.abs(r.nextInt());
            if(rand % 100 < MOUNTAIN_MOD || getRandom(occurance) == Mountain)
                index = 11;
            
        }
        else if(terrain == Sea){
            rand = Math.abs(r.nextInt());
            if(rand % 100 < OCEAN_MOD || getRandom(occurance) == Ocean){
                index = 1;
            }
        }
        if(index == 0)
            return tile;
        else
            return new Tile(terrains[index], x, y);
    }

    private static Tile createTile(int x, int y){
        int index = 0;
        int rand = Math.abs(r.nextInt());
        switch(getRandom()){
            case Sea:
                rand = Math.abs(r.nextInt());
                if(rand % 100 < WATER_MOD){
                    index = 0;
                }
                else{
                    index = 2;
                }
                break;
            default:
                rand = Math.abs(r.nextInt());
                if(rand % 100 < LAND_MOD){
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
        int rand = Math.abs(r.nextInt());
        return terrains[rand % terrains.length];
    }

    private static TerrainType getRandom(HashMap<TerrainType, Integer> map){
        int rand = Math.abs(r.nextInt());
        return (TerrainType)map.keySet().toArray()[rand % map.keySet().size()];
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
