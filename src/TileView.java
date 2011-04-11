import java.io.File;
import java.io.IOException;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TileView extends JPanel{

    private static final String imgPath = "../data/img/"; //Need a better fix for this!
    private int positionx;
    private int positiony;
    private Polygon area;
    private Tile tile;
    private BufferedImage normal;
    private BufferedImage fogged;

    /**
     * x and y in this case is pixels away from 
     * the top left corner of the GameMapView. 
     */
    public TileView(int x, int y, Tile tile){
        // Create polygon here
        positionx = x;
        positiony = y;
        this.tile = tile;
        setBounds(x, y, 175, 175);
        setOpaque(false);
        try{
            normal = ImageIO.read(new File(imgPath + tile.getTerrain().getTileImage()));
            //fogged = ImageIO.read(new File(tile.getTerrain().getFogImage()));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public int getTilePositionx(){
        return positionx;
    }

    public int getTilePositiony(){
        return positiony;
    }

    public boolean contains(int x, int y){
        return false;//area.contains(x, y);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image terrain;
        if(!tile.hasFog()){
            if(tile.isExplored()){
                // Tile is being seen by a unit
                terrain = normal;
            }
            else {
                // Tile is no longer being watched, so paint the foggy tile.
                terrain = fogged;
            }
            g.drawImage(terrain, 0, 0, this);
        }
        else{
            //Tile is in total fog so lets just paint it black
            g.setColor(Color.BLACK);
            //            g.fillPolygon(area);
        }
    }
}
