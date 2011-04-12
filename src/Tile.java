import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Tile {
    private boolean selected, explored, fog, plain;
    private static final String imgPath = "../data/img/"; //Need a better fix for this!

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
            }
            catch(IOException e){
                System.out.println(e);
            }
        }

        selected = false;
        explored = false;
        fog = true;
        countToFog = 0;
        view = new TileView(((x - y)*120)+120, ((x + y)*68), this);
    }

    public void select(){
        selected = true;
    }

    public void deselect(){
        selected = true;
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
        System.out.println(view.getTilePositionx());
        System.out.println(view.getTilePositiony());
        return view;
    }

    public TerrainType getTerrain(){
        return terrain;
    }

    public boolean isExplored(){
        return true; //explored;
    }

    public boolean hasFog(){
        return false; //fog;
    }
}
