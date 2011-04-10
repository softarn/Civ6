import java.io.File;
import java.io.IOException;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class TileView extends JComponent{

  public int positionx;
  public int positiony;
  public Polygon area;
  public Tile tile;

  /**
   * x and y in this case is pixels away from 
   * the top left corner of the GameMapView. 
   */
  public TileView(int x, int y, Tile tile){
    // Create polygon here
    positionx = x;
    positiony = y;
    this.tile = tile;
  }

  public int getTilePositionx(){
    return positionx;
  }

  public int getTilePositiony(){
    return positiony;
  }

  public boolean contains(int x, int y){
    return area.contains(x, y);
  }

  public void paint(Graphics g){
    File terrain;
    if(!tile.hasFog()){
      if(tile.isExplored()){
        // Tile is being seen by a unit
        terrain = new File(tile.getTerrain().getTileImage());
      }
      else {
        // Tile is no longer being watched, so paint the foggy tile.
        terrain = new File(tile.getTerrain().getFogImage());
      }
      try{
        g.drawImage(ImageIO.read(terrain), positionx, positiony, null);
      }
      catch(IOException e){
        System.out.println(e);
      }
    }
    else{
      //Tile is in total fog so lets just paint it black
      g.setColor(Color.BLACK);
      g.fillPolygon(area);
    }
  }
}
