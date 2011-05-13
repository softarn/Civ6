package civ;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

import proxy.Proxy;
import proxy.Result;
import proxy.FailedException;

class ConnectView extends JPanel implements ActionListener{
	
	private JLabel connectLabel = new JLabel("Anslut till server ");
	private JLabel serverLabel = new JLabel("Server IP: ");
	private JLabel portLabel = new JLabel("Port: ");
	private JLabel usernameLabel = new JLabel("Username: ");
	
	private JButton connectButton = new JButton("Anslut ");
	
	private JTextField serverLine = new JTextField("dvk.fishface.se");
	private JTextField portLine = new JTextField("1339");
	private JTextField usernameLine = new JTextField("amoros");
	
	private int port;
	private Proxy p;
	private Result returned;
	
	private LobbyScreen ls;

	public ConnectView(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
        /*try{
            returned = p.connect(usernameLine.getText());
            System.out.println(returned.getOk() + "   " + returned.getOkMsg());
        }
        catch(FailedException fe){
            System.out.println(fe);
        }*/
        
        connectButton.addActionListener(this);
        
        serverLabel.setLabelFor(serverLine);
        portLabel.setLabelFor(portLine);
        usernameLabel.setLabelFor(usernameLine);
        
        add(serverLabel);
        add(portLabel);
        add(usernameLabel);
		add(connectLabel);
		
		add(connectButton);
		
        add(serverLine);
		add(portLine);
		add(usernameLine);

	}

	// Here goes proxy, package and so forth
	
	public void actionPerformed (ActionEvent ae) {
		if(ae.getSource() == connectButton) {
			try{
                p = new Proxy(serverLine.getText(), Integer.valueOf(portLine.getText()), new MyPacketListener());
                Result r = p.connect(usernameLine.getText());
                GameServer.init(p);
                Round.addPlayer(Player.getInstance(usernameLine.getText()));
                Round.setMe(Player.getInstance(usernameLine.getText()));
                if(!r.getOk()){
                    System.out.println("Connection failed");
                    return;
                }
            	//returned = p.listGames();
                //System.out.println(returned.getSessions());
            }
            catch(FailedException fe){
                System.out.println(fe);
            }     
            
            ls = new LobbyScreen();
            JFrame frame = (JFrame)SwingUtilities.getRoot(this);
            frame.getContentPane().remove(this);
            frame.getContentPane().add(ls);
            frame.getContentPane().validate();      		
        }
	
	}
	
}
