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
    private double maxX, maxY;
    // The clickable hexagon
    private Polygon area;
    // The graphical hexagon
    private Polygon aura;
    private Tile tile;
    private int[] xs = {43, 3, 43, 121, 160, 121};
    private int[] ys = {35, 104, 170, 170, 104, 35};

    /**
     * x and y in this case is pixels away from 
     * the top left corner of the GameMapView. 
     */
    public TileView(int x, int y, Tile tile){
        area = new Polygon(xs,ys,6);
        area.translate(x, y);
        aura = new Polygon(xs,ys,6);
        positionx = x;
        positiony = y;
        maxX = 175;
        maxY = 175;
        this.tile = tile;
        setBounds(x, y, (int)maxX, (int)maxY);

        setOpaque(false);
    }

    public void resize(double xy){
        maxX = xy;
        maxY = xy;

        int[] tempXs = new int[6];
        int[] tempYs = new int[6];
        int tempX = (int)(getX() * maxX / getWidth());
        int tempY = (int)(getY() * maxY / getHeight());
        setBounds(tempX, tempY, getWidth(), getHeight());
        for(int i=0; i<6; ++i){
            tempXs[i] = (int)(xs[i] * maxX / getWidth());
            tempYs[i] = (int)(ys[i] * maxY / getHeight());
        }
        area = new Polygon(tempXs, tempYs, 6);
        // area.translate((int)(tX * getWidth() / maxX), (int)(tY * getHeight() / maxY));
        area.translate(tempX, tempY);
        repaint();
    }

    public boolean contains(int x, int y){
        return area.contains(x, y);
    }

    public int getTilePositionx(){
        return positionx;
    }

    public int getTilePositiony(){
        return positiony;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Image terrain;
        g2.scale(
                (double) maxX / getWidth(),
                (double) maxY / getHeight());
        if(tile.isExplored()){
            if(tile.hasFog()){
                terrain = tile.getTileFogImg();
            }else{
                terrain = tile.getTileImg();
            }

            g2.drawImage(terrain, 0, 0, this);

            if(tile.isSelected()){
                g2.setColor(Color.YELLOW);
                g2.setStroke(new BasicStroke(3));
                g2.drawPolygon(aura);
            }
        }else{
            //Tile is in total fog so lets just paint it black
            g2.setColor(Color.BLACK);
            g2.fillPolygon(aura);
        }
        if(tile.isHilighted()){
            g2.setColor(new Color(240, 200, 50, 120));
            g2.fillPolygon(aura);
        }

        if(tile.hasUnit() && !tile.hasFog()){
            BufferedImage unitImg = tile.getUnitImg();

            int h = unitImg.getTileHeight();
            int w = unitImg.getTileWidth();

            int x = (w/2) + 20;
            int y = 150 - h;
            g2.drawImage(unitImg, x, y, this);
        }
    }
}
