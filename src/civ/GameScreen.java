package civ;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

import proxy.Proxy;
import proxy.Result;
import proxy.FailedException;

class GameScreen extends JPanel implements ActionListener {
    private static GameScreen object;
	private JLabel userLabel = new JLabel("Users: 5");
	private JButton startButton = new JButton("Starta ");
	private JButton lockButton = new JButton("Lås ");
	
	private boolean startEnabled = true; 
	
	private JPanel topPanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	private String[] players = {};
		
	private JList list = new JList(players);
	
	private GameScreen(){
		setLayout(new BorderLayout());
		
		topPanel.add(userLabel);
		
		listPanel.add(list);
		
		buttonPanel.add(startButton);
		buttonPanel.add(lockButton);
		startButton.addActionListener(this);
		lockButton.addActionListener(this);
		
		topPanel.setLayout(new FlowLayout());
		listPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		
		add(topPanel, BorderLayout.NORTH);
		add(listPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

    public void init(boolean startEnabled){
		if(startEnabled == false){
			startButton.setEnabled(false);
			lockButton.setEnabled(false);
			
		}
    }

    public static GameScreen getInstance(){
        if(object == null) object = new GameScreen();
        return object;
    }
	
    public void update(){
        list.setListData(Round.getPlayers());
    }

	
	public void actionPerformed (ActionEvent ae) {
		if(ae.getSource() == startButton)
			System.out.println("startButton pressed ");
		else if (ae.getSource() == lockButton)
			System.out.println("lockButton pressed ");
	}
	
}
