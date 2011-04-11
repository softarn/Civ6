public class Tile {
    private boolean explored, fog, plain;
    private int countToFog;
    private int x, y;

    private TerrainType terrain;
    private PhysicalUnit unit;
    private TileView view;

    public Tile(){
        //Temporary empty tile constructor, remove this when it is not needed anymore.
    }
    public Tile(TerrainType tt, int x, int y){
        explored = false;
        fog = true;
        countToFog = 0;

        terrain = tt;
        this.plain = plain;
        this.x = x;
        this.y = y;
        view = new TileView(((x - y)*120)+120, ((x + y)*68), this);
    }

    public void setUnit(PhysicalUnit pu){
        unit = pu;
    }

    public PhysicalUnit getUnit(){
        return unit;
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
