package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

import static src.State.TileState.Selected;
import static src.State.TileState.UnSelected;

public class Tile {
    private boolean selected, hilight, explored, plain;

    private int countToFog;
    private int x, y;

    private TerrainType terrain;
    private PhysicalUnit unit;
    private TileView view;

    public Tile(TerrainType tt, PhysicalUnit pu, int x, int y){
        init(tt, pu, x, y);
    }
    public Tile(TerrainType tt, int x, int y){
        init(tt, null, x, y);
    }

    private void init(TerrainType tt, PhysicalUnit pu, int x, int y){
        terrain = tt;
        unit = pu;
        this.x = x;
        this.y = y;

        hilight = false;
        selected = false;
        explored = false;
        countToFog = 0;
        view = new TileView(((x - y)*120)+120, ((x + y)*68), this);

        setUnit(pu);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void select(){
        State st = State.getInstance();
        if(st.getTileState() == Selected){
            State.getSelectedTile().deselect();
        }
        st.setTileState(Selected);
        st.setSelectedTile(this);
        if(unit != null){
            st.setUnitState(State.UnitState.Selected);
            st.setSelectedUnit(unit);
        }
        selected = true;
        view.repaint();
    }

    public void deselect(){
        State st = State.getInstance();
        if(st.getSelectedTile().equals(this)){
            st.setTileState(UnSelected);
            st.setSelectedTile(null);
            selected = false;
            if(unit != null){
                st.setUnitState(State.UnitState.UnSelected);
                st.setSelectedUnit(null);
            }
            view.repaint();
        }
    }

    public boolean isSelected(){
        return selected;
    }

    //This function must be edited 
    public void setUnit(PhysicalUnit pu){
        if(pu != null){
           setExplored(true);
        }else{
            countToFog = 0;
        }
        unit = pu;
    }

    public PhysicalUnit getUnit(){
        return unit;
    }

    public boolean hasUnit(){
        return unit != null;
    }

    public BufferedImage getUnitImg(){
        return unit.getImage();
    }
    
    public BufferedImage getTileImg(){
        return terrain.getNormalImage();
    }

    public BufferedImage getTileFogImg(){
        return terrain.getFogImage();
    }

    public TileView getView(){
        return view;
    }

    public TerrainType getTerrain(){
        return terrain;
    }

    public void hilight(){
        hilight = true;
    }

    public void dehilight(){
        hilight = false;
    }

    public boolean isHilighted(){
        return hilight;
    }

    //Must be edited later
    public void setExplored(boolean bool){
        countToFog = 1;
        explored = bool;
    }

    public boolean isExplored(){
        return explored;
    }

    public boolean hasFog(){
        return countToFog == 0;
    }
}
