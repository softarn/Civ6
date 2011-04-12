package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

import static src.State.TileState.Selected;
import static src.State.TileState.UnSelected;

public class Tile {
    private boolean selected, hilight, explored, fog, plain;
    private static final String imgPath = "data/img/"; //Need a better fix for this!

    private int countToFog;
    private int x, y;

    private TerrainType terrain;
    private PhysicalUnit unit;
    private TileView view;
    private BufferedImage unitImg;

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

        if(pu != null){
            try{
                unitImg = ImageIO.read(new File(imgPath + pu.getType().getUnitImage()));
            }catch(IOException e){
                System.out.println(e);
            }
        }

        hilight = false;
        selected = false;
        explored = false;
        fog = true;
        countToFog = 0;
        view = new TileView(((x - y)*120)+120, ((x + y)*68), this);
    }

    public static int calcDist(Tile t1, Tile t2){
        int x1 = t1.getX();
        int x2 = t2.getX();

        int y1 = t1.getY();
        int y2 = t2.getY();

        System.out.println("x1: " +x1+ "y1: "+y1);
/*
        if(x1 > x2)
            return Tile.calcDist(t2, t1);
        else if(y2>=y1)
            return x2-x1 + y2-y1;
        else
            return Math.max(x2-x1, y1-y2);
            */
        return Math.max(Math.max(Math.abs(x1-x2), Math.abs(y1-y2)), Math.abs(Math.abs(x1-x2) + Math.abs(y1-y2)));
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

    public void setUnit(PhysicalUnit pu){
        unit = pu;
    }

    public PhysicalUnit getUnit(){
        return unit;
    }

    public boolean hasUnit(){
        return unit != null;
    }

    public BufferedImage getUnitImg(){
        return unitImg;
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

    public boolean isExplored(){
        return true; //explored;
    }

    public boolean hasFog(){
        return false; //fog;
    }
}
