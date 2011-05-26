package civ;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

class MapEdit  extends JPanel{
	private int min;
	private int max;
	private int init;
	private int intSize;
	
	private JLabel titleLabel;
	private JSlider jSlide;	
	private JButton okButton;
	private JButton cancelButton;
	
	private JPanel buttonPanel;
	private JPanel slidePanel;
	
	public MapEdit(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(70,30)));
		
		min = 2;
		max = 30;
		init = 20;
		intSize = 0;
		
		jSlide = new JSlider(JSlider.HORIZONTAL,
			min, max, init);	
		jSlide.setMajorTickSpacing(2);
		jSlide.setPaintTicks(true);
		jSlide.setPaintLabels(true);
		jSlide.setPaintTrack(true);
		
		titleLabel = new JLabel("Välj hur många tiles du vill ha på kartan: ");
		okButton = new JButton("OK");
		cancelButton = new JButton("Avbryt");
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		slidePanel = new JPanel();
		slidePanel.setLayout(new BoxLayout(slidePanel,BoxLayout.Y_AXIS));
		
		//ActionListener al = new ActionListener();
		//ChangeListener cl = new ActionListener();
		
		ActionListener butLis = new ButtonListener(); 
		
		jSlide.addChangeListener(new SlideListener());
		okButton.addActionListener(butLis);
		cancelButton.addActionListener(butLis);
		
		add(slidePanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		slidePanel.add(titleLabel);
		slidePanel.add(Box.createRigidArea(new Dimension(20,20)));
		slidePanel.add(jSlide);
		slidePanel.add(Box.createRigidArea(new Dimension(20,20)));
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
	}
	
	public void startGame (){
		setMapSize();
		State.setOnline(false);
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		frame.dispose();
		
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        new Window(size.width,size.height);   
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
    
	public void setMapSize(){
		if(intSize == 0){
			GameMap.getInstance().setHeight(init);
			GameMap.getInstance().setWidth(init);
		}
		else {
			GameMap.getInstance().setHeight(intSize);
			GameMap.getInstance().setWidth(intSize);				
		}
	}
    
    class ButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent ae) {
			if(ae.getSource() == okButton){
				startGame();
      		}
      	  	
      		if(ae.getSource() == cancelButton) {
      	  		System.exit(0);  
      		}
     	}
    }
}