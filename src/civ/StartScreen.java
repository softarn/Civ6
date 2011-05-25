package civ;

import javax.swing.*;
import javax.swing.BoxLayout;

import java.awt.Image;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Graphics;

import java.io.File;
import javax.imageio.ImageIO;

public class StartScreen extends JFrame implements ActionListener{
	
	private JButton singelButton;
	private JButton multiButton;
	private JButton preferencesButton;
	private JButton credButton;
	private JButton exitButton;
	
	private JPanel startPanel = new JPanel();
	private ConnectView cv;
	private Image backimg;
	private ImageIcon icon;
	private MapEdit mapEdit;
	
	public StartScreen(){
		super("Civilisering VI Hyper Gold  Edition");
		setLayout(new BorderLayout());
		
		//MediaTracker mt = new MediaTracker(this);
		//backimg = Toolkit.getDefaultToolkit().getImage("data/img/victor59wallpaper.png");
		//mt.addImage(backimg, 0);
		
        backimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/data/img/victor59wallpaper.png"));
        
		
		add(startPanel, BorderLayout.NORTH);	
		
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
		startPanel.add(Box.createRigidArea(new Dimension(80,60)));
	
		singelButton = new JButton("Ensamspelarläge");
		singelButton.addActionListener(this);

		multiButton = new JButton("Flerspelarläge");
		multiButton.addActionListener(this);
		
		preferencesButton = new JButton("Inställningar");
		preferencesButton.addActionListener(this);
		
		credButton = new JButton("Licenser och 'Tack till'");
		credButton.addActionListener(this);
		
		exitButton = new JButton("Avsluta");
		exitButton.addActionListener(this);
		
		startPanel.add(singelButton);
		startPanel.add(Box.createRigidArea(new Dimension(10,10)));
		
		startPanel.add(multiButton);
		startPanel.add(Box.createRigidArea(new Dimension(10,10)));
		
		startPanel.add(preferencesButton);
		startPanel.add(Box.createRigidArea(new Dimension(10,10)));

		startPanel.add(credButton);	
		startPanel.add(Box.createRigidArea(new Dimension(10,10)));

		startPanel.add(exitButton);
		startPanel.add(Box.createRigidArea(new Dimension(10,10)));
		
		startPanel.setOpaque(false);
		
		setLocation(270,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
		
	}
	
	public JFrame getFrame(){
		return this;	
	}	
	
	public void paintComponent(Graphics g) {
		//paintComponent(g);
		g.drawImage(backimg, 0, 0, null);
	}
	
	public void actionPerformed (ActionEvent ae){
		if (ae.getSource() == singelButton){
			
			mapEdit = new MapEdit();
			this.remove(startPanel);
			this.add(mapEdit);
			mapEdit.setVisible(true);
			this.validate();
			
			/*System.out.println("SingelButton ");
            State.setOnline(false);
            this.dispose();
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            new Window(size.width,size.height);*/
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
		
		else if (ae.getSource() == credButton) {
			System.out.println("");
		}
		
		else if (ae.getSource() == exitButton)
			System.exit(0);
		}

}










