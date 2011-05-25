package civ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;

import proxy.Proxy;
import proxy.Result;
import proxy.FailedException;

class LobbyScreen extends JPanel implements ActionListener {
	
	private JPanel buttonPanel = new JPanel(); 	
	
	private JButton hostButton = new JButton("Starta eget spel");
	private JButton joinButton = new JButton("Gå med i spel");
	private JButton refreshButton = new JButton("Uppdatera");
	
	private String[] players = {};
	
	private JList lobbyList = new JList(players);
	private JScrollPane scp = new JScrollPane(lobbyList);

	private GameScreen gs;
	private boolean startEnabled;
 	
	public LobbyScreen(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		hostButton.addActionListener(this);
		joinButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
        lobbyList.setListData(GameServer.listGames());
		lobbyList.setSelectedIndex(1);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(hostButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(110,10)));
		buttonPanel.add(joinButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,10)));
		buttonPanel.add(refreshButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,10)));
		
		add(scp);
		add(buttonPanel);
		
	}

	public void actionPerformed (ActionEvent ae) {
		if(ae.getSource() == hostButton){
			startEnabled = true;
			gs = GameScreen.getInstance();
            gs.init(startEnabled);
			
			JFrame frame = (JFrame)SwingUtilities.getRoot(this);
            frame.getContentPane().remove(this);
            frame.getContentPane().add(gs);
            frame.getContentPane().validate();  
						
			System.out.println("hostButton pressed ");
            GameServer.host();
		}
		
		else if (ae.getSource() == joinButton){
			startEnabled = false;
			gs = GameScreen.getInstance();
            gs.init(startEnabled);
			
			JFrame frame = (JFrame)SwingUtilities.getRoot(this);
            frame.getContentPane().remove(this);
            frame.getContentPane().add(gs);
            frame.getContentPane().validate();
            
			System.out.println("joinButton pressed ");
			GameServer.join((String)lobbyList.getSelectedValue());
		}
		else if (ae.getSource() == refreshButton){
            String[] data = GameServer.listGames();
            System.out.println(new ArrayList<String>(Arrays.asList(data)));
            lobbyList.setListData(data);
        }
	}
}

