package civ;

import javax.swing.*;
import java.awt.*;

class UnitTabView extends JPanel{
	private JTabbedPane unitTypePane = new JTabbedPane();
	private JPanel viewAllPanel = new JPanel();
	private JLabel enhetsNamn = new JLabel();
	//private Tile t = new Tile(); 
	//private PhyiscalUnit pu = new PhysicalUnit();
	
	//private ArrayList<PhysicalUnit> allUnitsArray = new ArrayList<PhysicalUnit> ();
	//private GameMap gm = new GameMap(null);
	
	public UnitTabView(){ 
		setLayout(new BorderLayout());

		unitTypePane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=10>Alla </body></html>", null, viewAllPanel, "Visa alla enheter ");
		unitTypePane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=10>Fotburna </body></html>", null, null, "Visa fotburna enhter ");
		unitTypePane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=10>Ryttare </body></html>", null, null, "Visa beridna enheter ");
		unitTypePane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=10>Båtar </body></html>", null, null, "Visa båtarburna enheter ");
		unitTypePane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=10>Artilleri </body></html> ", null, null, "Visa artillerienheter ");
		unitTypePane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=10>Civila </body></html>", null, null, "Visa civila enheter ");

		//viewAllPanel.add();	
		
		//this.printUnitArray();
		
		add(unitTypePane, BorderLayout.NORTH);
		
	}
	
	/*public void printUnitArray() {
		for (PhysicalUnit pu : t.getUnitArray()){
			enhetsNamn.setText(pu.toString());		
		}
		
		viewAllPanel.add(enhetsNamn);
		unitTypePane.add(viewAllPanel);
		unitTypePane.repaint();
	}*/
}




















