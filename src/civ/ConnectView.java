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
	
	private LobbyScreen ls = new LobbyScreen();

	public ConnectView(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		p = new Proxy(serverLine.getText(), Integer.valueOf(portLine.getText()), new MyPacketListener());
		
        try{
            returned = p.connect(usernameLine.getText());
            System.out.println(returned.getOk() + "   " + returned.getOkMsg());
        }
        catch(FailedException fe){
            System.out.println(fe);
        }
        
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
            	returned = p.listGames();
                System.out.println(returned.getSessions());
            }
            catch(FailedException fe){
                System.out.println(fe);
            }     
            
            JFrame frame = (JFrame)SwingUtilities.getRoot(this);
            frame.getContentPane().remove(this);
            frame.getContentPane().add(ls);
            frame.getContentPane().validate();      		
        }
	
	}
	
}
