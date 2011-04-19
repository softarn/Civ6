import civ.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class GameMapTest{
    private GameMap gm;
    private GameMapView gmv;

    @Before public void setUp(){
        gmv = new GameMapView();
        gm = new GameMap(gmv);
    } 

    @Test public void test(){
        System.out.println("hej");
        assertEquals(1,1);
    }
}
