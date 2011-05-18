package civ;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class StartScreen extends JFrame implements ActionListener{
	
	private JButton singelButton;
	private JButton multiButton;
	private JButton exitButton;
	private JPanel startPanel = new JPanel();
	private ConnectView cv;
	
	public StartScreen(){
			super("Civ6 ");
			
			singelButton = new JButton("Singelplayer ");
			singelButton.addActionListener(this);
			multiButton = new JButton("Multiplayer  ");
			multiButton.addActionListener(this);
			exitButton = new JButton("Avsluta ");
			exitButton.addActionListener(this);
		
			add(startPanel);
			startPanel.setLayout(new FlowLayout());
			startPanel.add(singelButton);
			startPanel.add(multiButton);
			startPanel.add(exitButton);
			
			setLocation(270,100);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(300, 400);
			setVisible(true);
	}
	
	public JFrame getFrame(){
		return this;	
	}
	
	public void actionPerformed (ActionEvent ae){
		if (ae.getSource() == singelButton){
			System.out.println("SingelButton ");
            State.setOnline(false);
            this.dispose();
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            new Window(size.width,size.height);
        }

		else if (ae.getSource() == multiButton) {
			System.out.println("Visa multi!!");
            State.setOnline(true);
            cv = new ConnectView();
			this.remove(startPanel);
			this.add(cv);
			cv.setVisible(true);
			this.validate();
		}
		
		else if (ae.getSource() == exitButton)
			System.exit(0);
		}

}










