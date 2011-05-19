package civ;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPort extends JPanel implements ActionListener{
    private GameMapView gmv;
    private static JPanel popup = new JPanel();
    private static final State state = State.getInstance();
    private static ViewPopup vp = new ViewPopup();
    private JButton exitButton = new JButton("X");

    public ViewPort(){
        setLayout(null);
        gmv = new GameMapView();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        popup.setBounds(size.width/4,size.height/8,700,400);
        popup.setVisible(false);
        popup.setLayout(new BorderLayout());
        
        exitButton.addActionListener(this);
		exitButton.setPreferredSize(new Dimension(50,30));
		exitButton.setBackground(new Color (0xEE2222));
		exitButton.setForeground(new Color (0xFFFFFF));
		popup.add(exitButton, BorderLayout.NORTH);
		
		
        popup.add(vp, BorderLayout.CENTER);
        popup.setOpaque(true);
        
        add(popup);
        add(gmv);
    }

    public static ViewPopup getTabbed(){
        return vp;
    }
    
    public static JPanel getPopup(){
    	return popup;	
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == exitButton)
			popup.setVisible(false);
	}
}
