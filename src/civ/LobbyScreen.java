package civ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import proxy.Proxy;
import proxy.Result;
import proxy.FailedException;

class LobbyScreen extends JPanel implements ActionListener {
	
	private JPanel buttonPanel = new JPanel(); 	

	private JButton hostButton = new JButton("Host ");
	private JButton joinButton = new JButton("Join ");
	private JButton refreshButton = new JButton("Refresh");
	
	private String[] players = {};
	
	private JList lobbyList = new JList(players);
	private JScrollPane scp = new JScrollPane(lobbyList);

	private GameScreen gs;
	private boolean startEnabled;
 	
	public LobbyScreen(){
		setLayout(new BorderLayout());
		
		scp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		hostButton.addActionListener(this);
		joinButton.addActionListener(this);
		
		lobbyList.setSelectedIndex(1);
		
		buttonPanel.add(hostButton);
		buttonPanel.add(joinButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		add(scp, BorderLayout.NORTH);	
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
			
		}
		else if (ae.getSource() == refreshButton){
            lobbyList.setListData(GameServer.listGames());
        }
	}
}

