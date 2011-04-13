package src;

import java.awt.Polygon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TileView extends JPanel{

    private int positionx;
    private int positiony;
    private Polygon area;
    private Tile tile;

    /**
     * x and y in this case is pixels away from 
     * the top left corner of the GameMapView. 
     */
    public TileView(int x, int y, Tile tile){
        // Create polygon here
        int[] xs = {43, 3, 43, 121, 160, 121};
        int[] ys = {35, 104, 170, 170, 104, 35};
        area = new Polygon(xs,ys,6);
        area.translate(x, y);
        positionx = x;
        positiony = y;
        this.tile = tile;

        setBounds(x, y, 175, 175);
        setOpaque(false);
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image terrain;
        area.translate(-positionx, -positiony);
        if(tile.isExplored()){
            if(tile.hasFog()){
                terrain = tile.getTileFogImg();
            }else{
                terrain = tile.getTileImg();
            }

            g.drawImage(terrain, 0, 0, this);

            if(tile.isSelected()){
                g.setColor(Color.YELLOW);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                g2.drawPolygon(area);
            }

            if(tile.isHilighted()){
                g.setColor(new Color(240, 200, 50, 120));
                g.fillPolygon(area);
            }

            if(tile.hasUnit()){
                BufferedImage unitImg = tile.getUnitImg();

                int h = unitImg.getTileHeight();
                int w = unitImg.getTileWidth();

                int x = (w/2) + 20;
                int y = 150 - h;
                g.drawImage(unitImg, x, y, this);
            }

        }else{
            //Tile is in total fog so lets just paint it black
            g.setColor(Color.BLACK);
            g.fillPolygon(area);
        }
        area.translate(positionx, positiony);
    }
}
