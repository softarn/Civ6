package civ;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;	

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPopup extends JPanel implements ActionListener{
	private JTabbedPane mainTPane;
	private JPanel closePanel;
	private JButton exitButton = new JButton("X");
	private UnitTabView utv = new UnitTabView();
	
	public ViewPopup() {
		setLayout(new BorderLayout());
		mainTPane = new JTabbedPane();
		closePanel = new JPanel();
		
		exitButton.addActionListener(this);
		exitButton.setPreferredSize(new Dimension(50,30));
		exitButton.setBackground(new Color (0xEE2222));
		exitButton.setForeground(new Color (0xFFFFFF));
		
		mainTPane.addTab("Enheter ", null, null, "Tooltips vare her ");
        mainTPane.setPreferredSize(new Dimension(580,200));
	
		mainTPane.addTab("Städer", null, null, "Tooltips vare her ");
		mainTPane.addTab("Forskning", null, null, "Tooltips vare her ");
		//mainTPane.addTab("Mecca", null, Content, "Tooltips vare her ");
		//add(unitContent);
		
		//closePanel.add(exitButton);
		//closePanel.setPreferredSize(new Dimension (30,40));
		
		add(mainTPane, BorderLayout.SOUTH);
		add(exitButton, BorderLayout.EAST);
		
		//mainTPane.repaint();
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == exitButton)
			System.exit(0);
	}	
		
	
}
