package civ;

import javax.swing.*;
import java.awt.*;

class UnitTabView extends JPanel{
	private JTabbedPane unitTypePane = new JTabbedPane();
	private JPanel viewAllPanel= new JPanel();
	private JLabel enhetsNamn = new JLabel();
	
	public UnitTabView(){ 
		setLayout(new BorderLayout());

		unitTypePane.addTab("Alla ", null, null, "Visa alla enheter ");
		unitTypePane.addTab("Fotburna ", null, null, "Visa fotburna enhter ");
		unitTypePane.addTab("Ryttare ", null, null, "Visa beridna enheter ");
		unitTypePane.addTab("Båtar ", null, null, "Visa båtarburna enheter ");
		unitTypePane.addTab("Artilleri ", null, null, "Visa artillerienheter ");
		unitTypePane.addTab("Civila ", null, null, "Visa civila enheter ");

				
		//viewAllPanel.add();	
		
		add(unitTypePane, BorderLayout.NORTH);
		
	}
	
}
