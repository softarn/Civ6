package civ;

import java.awt.Image;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

import static civ.State.TileState.TileSelected;
import static civ.State.TileState.TileUnSelected;
import static civ.State.CityState.CitySelected;
import static civ.State.CityState.CityUnSelected;
import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;

public class Tile implements Comparable<Tile>{
    private static final State state = State.getInstance();
    private boolean selected, hilight, explored, plain;
    private Color color;

    private int countToFog;
    private int x, y;

    private TerrainType terrain;
    private PhysicalUnit unit;
    private City city;
    private TileView view;

    private int[][] offsets = {{-1,-1},
                            {0,-1},
                            {1,0},
                            {1,1},
                            {0,1},
                            {-1,0}};

    public Tile(){
        // Empty constructor does nothing
    }
    public Tile(TerrainType tt, PhysicalUnit pu, int x, int y){
        init(tt, pu, null, x, y);
    }
    public Tile(TerrainType tt, int x, int y){
        init(tt, null, null, x, y);
    }
    public Tile (TerrainType tt, City city, int x, int y) {
        init(tt, null, city, x, y);

    }

    private void init(TerrainType tt, PhysicalUnit pu, City city, int x, int y){
        terrain = tt;
        unit = pu;
        this.city = city;
        this.x = x;
        this.y = y;

        hilight = false;
        selected = false;
        explored = false;
        countToFog = 0;
        /**
         * pixelx = ((x - y) * bredd) + offsetX
         * pixely = ((x + y) * height / 2) + offsetY
         */

        view = new TileView(((x - y)*120)+120*GameMap.getInstance().getWidth(), ((x + y)*68)+170, this);

        setUnit(pu);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void select(){
        if(state.getTileState() == TileSelected){
            State.getSelectedTile().deselect();
        }
        state.setSelectedTile(this);
        state.setTileState(TileSelected);
        if(hasCity()){
        	state.setSelectedCity(city);
        	state.setCityState(CitySelected);
        }
        if(hasUnit()){
            state.setSelectedUnit(unit);
            state.setUnitState(UnitSelected);
        }
        selected = true;
        view.repaint();
    }

    public void deselect(){
        if(state.getSelectedTile().equals(this)){
            state.setSelectedTile(null);
            state.setTileState(TileUnSelected);
            selected = false;

            state.setSelectedCity(null);
            state.setCityState(CityUnSelected);

            state.setSelectedUnit(null);
            state.setUnitState(UnitUnSelected);
            view.repaint();
        }
    }

    public boolean isSelected(){
        return selected;
    }
    
    public void setCity(City city){
        this.city = city;
    }
    

    //This function must be edited 
    public boolean setUnit(PhysicalUnit pu){
        if(pu == null){
            unit = null;
            return true;
        }
        if(hasCity()){
            if(pu != null && (pu.isAlly() || city.getHold().isEmpty())){
                Hold hold = city.getHold();
                if(!hold.addUnit(pu)){
                    System.out.println("No room for unit");
                    return false;
                }
                state.setUnitState(UnitUnSelected);
                if(!pu.isAlly()){
                    city.setOwner(null);
                    state.setCityState(CityUnSelected);
                }
            }
        }
        else if(hasUnit() && unit.getHold() != null){
            if(pu != null && pu.isAlly()){
                Hold hold = getUnit().getHold();
                if(!hold.addUnit(pu)){
                    System.out.println("No room for unit");
                    return false;
                }
            }
        }
        else{
            if(pu != null && pu.isAlly()){
                setExplored(true);
                if(GameMap.getInstance() != null){
                    for(Tile t : GameMap.getInstance().getNeighbours(this, pu.getType().getVision())){
                        t.setExplored(true);
                        t.getView().repaint();
                    }
                }
            }else{
                //countToFog = 0;
            }
            unit = pu;
        }
        view.repaint();
        return true;
    }

    public PhysicalUnit getUnit(){
        return unit; 
    }

    public City getCity() {
    	return city;	
    }

    public boolean hasCity() {
    	return city!=null;	
    }
    
    public boolean hasUnit(){
        return unit != null;
    }

    public Image getUnitImg(){
        return unit.getImage();
    }
    
    public Image getTileImg(){
        return terrain.getNormalImage();
    }

    public Image getTileFogImg(){
        return terrain.getFogImage();
    }

    public TileView getView(){
        return view;
    }

    public TerrainType getTerrain(){
        return terrain;
    }

    public void hilight(Color color){
        hilight = true;
        this.color = color;
    }

    public void dehilight(){
        hilight = false;
    }

    public Color getHilightColor(){
        return color;
    }

    public boolean isHilighted(){
        return hilight;
    }

    //Must be edited later
    public void setExplored(boolean bool){
        if(bool){
            countToFog = 80;
        }
        explored = bool;
    }

    public void decreaseFogCounter(){
        --countToFog;
        if(countToFog == 0){
            setExplored(false);
        }
    }

    public boolean isExplored(){
        return explored;
    }

    public boolean hasShadow(){
        return countToFog <= 78;
    }
    
    public void setShadow(){
	    countToFog = 78;
    }

    public boolean hasFog(){
        return countToFog <= 40;
    }

    /**
     * Get all neighbours to a tile in an ArrayList(Not finished yet!)
     * @param tile Neighbours to this tile will be returned
     * @param range Neighbours in this range will be returned.
     */
    public ArrayList<int[]> getNeighbours(int range){
        if(range < 1){
            return new ArrayList<int[]>();
        }
        int x = getX();
        int y = getY();

        ArrayList<int[]> acc = new ArrayList<int[]>();

        for(int[] off : offsets){
            int[] t = {x - off[0], y - off[1]}; //Tile t = getTile(x - off[0], y - off[1]);
            if(t != null)
                acc.add(t);
        }
        if(range > 1){
            for(int[] off : offsets){
                int[] t = {x - off[0], y - off[1]}; //Tile t = getTile(x - off[0], y - off[1]);
                acc.addAll(getNeighbours(t, range - 1, acc));
            }
        }

        return acc;
    }

    // Get neighbours within a range of 1 of the tile.
    private ArrayList<int[]> getNeighbours(int[] tile){
        ArrayList<int[]> tmp = new ArrayList<int[]>();
        for(int[] off : offsets){
            int[] t = {x - off[0], y - off[1]}; //Tile t = getTile(x - off[0], y - off[1]);
            if(t != null)
                tmp.add(t);
        }
        return tmp;
    }

    private ArrayList<int[]> getNeighbours(int[] tile, int range, ArrayList<int[]> acc){
        if(range < 1){
            return new ArrayList<int[]>();
        }
        int x = tile[0];
        int y = tile[1];

        ArrayList<int[]> tmp = new ArrayList<int[]>();

        for(int[] t2 : getNeighbours(tile)){
            if(!acc.contains(t2))
                tmp.add(t2);
        }
        if(range > 1){
            for(int[] off : offsets){
                int[] t = {x - off[0], y - off[1]}; //Tile t = getTile(x - off[0], y - off[1]);
                acc.addAll(getNeighbours(t, range - 1, acc));
            }
        }

        return acc;            
    }

    public boolean equals(Object other){
        if(other instanceof Tile){
            Tile oth = (Tile)other;
            if(this.x == oth.x && this.y == oth.y){
                return true;
            }
        }
        return false;
    }

    public int compareTo(Tile other){
        return (this.x * this.y) - (other.x * other.y);
    }
}
