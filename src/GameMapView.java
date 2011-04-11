import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameMapView extends JPanel{

  public GameMapView(){
    super();
    new GameMap(this);
    setLayout(null);
  }
}
