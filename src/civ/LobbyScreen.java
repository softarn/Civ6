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
		refreshButton.addActionListener(this);
		
        lobbyList.setListData(GameServer.listGames());
		lobbyList.setSelectedIndex(1);
		
		buttonPanel.add(hostButton);
		buttonPanel.add(joinButton);
		buttonPanel.add(refreshButton);
		
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

