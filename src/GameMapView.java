import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class GameMapView extends JPanel{

  public GameMapView(){
    super();
    new GameMap(this);
    setLayout(null);
    setBackground(Color.black);
  }
}
