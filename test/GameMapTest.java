import civ.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class GameMapTest{
    private GameMap gm;
    private GameMapView gmv;

    @Before public void setUp(){
        gmv = new GameMapView();
        gm = GameMap.getInstance();
    } 

    @Test public void test(){
        assertEquals(1,1);
    }

    @Test public void testGetNeighbours(){
        ArrayList<Tile> expected = new ArrayList<Tile>();
        expected.add(gm.getTile(4,5));
        expected.add(gm.getTile(4,6));
        expected.add(gm.getTile(5,5));
        expected.add(gm.getTile(5,7));
        expected.add(gm.getTile(6,6));
        expected.add(gm.getTile(6,7));
        
        ArrayList<Tile> actual = gm.getNeighbours(gm.getTile(5,6), 1);
        
        Collections.sort(expected);
        Collections.sort(actual);
        
        assertEquals(expected, actual);
    }

    @Test public void testGetNeighbours2(){
        ArrayList<Tile> expected = new ArrayList<Tile>();
        expected.add(gm.getTile(3,4));
        expected.add(gm.getTile(3,5));
        expected.add(gm.getTile(3,6));
        expected.add(gm.getTile(4,4));
        expected.add(gm.getTile(4,5));
        expected.add(gm.getTile(4,6));
        expected.add(gm.getTile(4,7));
        expected.add(gm.getTile(5,4));
        expected.add(gm.getTile(5,5));
        expected.add(gm.getTile(5,7));
        expected.add(gm.getTile(5,8));
        expected.add(gm.getTile(6,5));
        expected.add(gm.getTile(6,6));
        expected.add(gm.getTile(6,7));
        expected.add(gm.getTile(6,8));
        expected.add(gm.getTile(7,6));
        expected.add(gm.getTile(7,7));
        expected.add(gm.getTile(7,8));

        ArrayList<Tile> actual = gm.getNeighbours(gm.getTile(5,6), 2);

        Collections.sort(expected);
        Collections.sort(actual);
        
        assertEquals(expected, actual);
    }
}
