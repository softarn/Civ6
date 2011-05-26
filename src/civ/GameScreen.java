package civ;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;

import proxy.Proxy;
import proxy.Result;
import proxy.FailedException;

class GameScreen extends JPanel implements ActionListener {
    private static GameScreen object;
    
	private JLabel userLabel;
	private JLabel civLabel;
	private JLabel nameLabel;
	
	private JTextField nameField;
	private JTextField civField;
	
	private JButton startButton;
	private JButton lockButton;
	
	private boolean startEnabled = true;
	private boolean toggle = true;
	
	private JPanel listPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel sliderPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	private int min;
	private int max;
	private int init;
	private int intSize;
	
	private JLabel titleLabel;
	private JSlider jSlide;	
	
	private String[] players = {};
		
	private JList list = new JList(players);
	private JScrollPane scp;
	
	private GameScreen(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		nameField = new JTextField("amoros");
		nameField.setBackground(Color.LIGHT_GRAY);
		civField = new JTextField("Persians");
		
		startButton = new JButton("Starta");
		lockButton = new JButton("Lås");
		
		int arrLength = Round.getPlayers().length;
		userLabel  = new JLabel("Antal spelare:  " + Integer.toString(arrLength) + " ");
		
		civLabel = new JLabel("Civilsationens namn: ");
		nameLabel = new JLabel("Spelarnamn: ");
		
		//list.setMinimumSize(new Dimension(40,50));
		scp = new JScrollPane(list);
		
		titleLabel = new JLabel("Välj hur många tiles du vill ha på kartan: ");

		min = 20;
		max = 200;
		init = 20;
		intSize = 0;
		
		jSlide = new JSlider(JSlider.HORIZONTAL, min, max, init);	
		jSlide.setMajorTickSpacing(50);
		jSlide.setPaintTicks(true);
		jSlide.setPaintLabels(true);
		jSlide.setPaintTrack(true);
		
		jSlide.addChangeListener(new SlideListener());
		
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(nameLabel);
		textPanel.add(nameField);
		textPanel.add(civLabel);
		textPanel.add(civField);
		
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(Box.createRigidArea(new Dimension(10,10)));
		rightPanel.add(userLabel);
		rightPanel.add(Box.createRigidArea(new Dimension(20,10)));
		rightPanel.add(scp);
		rightPanel.add(Box.createRigidArea(new Dimension(20,10)));
		//rightPanel.add(list);
		rightPanel.add(Box.createRigidArea(new Dimension(10,10)));
		
		update();
		listPanel.add(textPanel);
		listPanel.add(rightPanel);
		
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		sliderPanel.add(Box.createRigidArea(new Dimension(10,30)));
		sliderPanel.add(titleLabel);
		sliderPanel.add(Box.createRigidArea(new Dimension(10,30)));
		sliderPanel.add(jSlide);
		sliderPanel.add(Box.createRigidArea(new Dimension(10,30)));
		buttonPanel.add(Box.createRigidArea(new Dimension(10,30)));
		buttonPanel.add(startButton);
		buttonPanel.add(lockButton);
		
		startButton.addActionListener(this);
		lockButton.addActionListener(this);
		
		listPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		
		add(listPanel);
		add(sliderPanel);
		add(buttonPanel);
		
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
    
    
	class SlideListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        
        	if (!source.getValueIsAdjusting()){
            	intSize = (int)source.getValue();
            	System.out.println(intSize);
        	}
        }
    }
    
	
	public void actionPerformed (ActionEvent ae) {
		if(ae.getSource() == startButton){
			System.out.println("startButton pressed ");
			
			if(intSize == 0)
				intSize = init; 
			
            if(GameServer.startGame(intSize)){
                //Close the window here
                //JFrame frame = (JFrame)SwingUtilities.getRoot(this);
                //frame.dispose();
            }
            else{
                System.out.println("Game could not be started");
            }
        }
		else if (ae.getSource() == lockButton){
			System.out.println("lockButton pressed ");
            GameServer.lockGame(toggle);
            toggle = !toggle;
        }
	}
	
}
