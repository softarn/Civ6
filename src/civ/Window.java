		package civ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Window extends JFrame  implements ActionListener{

	//Menu bar
	private JMenuBar mainMenuBar;
	private JMenu mainMenu;
	private JMenuItem menuItem;
	private PopupWindow puw;	
	
    //Only for testpurpose
    public static void main(String[] args){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        new Window(size.width,size.height);
    }

    Window(int w,int h) {
        super ("Civ 6");
        Round.next(); // Round.next() has to be run atleast once before the player gets to play.
        	
		puw = new PopupWindow();
		
        Menu m = new Menu(puw);
        ViewPort vp = new ViewPort();

		mainMenuBar = new JMenuBar();
		mainMenu = new JMenu("Meny");
		
		menuItem = new JMenuItem("Avsluta");
		
		mainMenu.add(menuItem);
		mainMenuBar.add(mainMenu);
		
		menuItem.addActionListener(this);
		
		setJMenuBar(mainMenuBar);
		///add(mainMenu);
		///add(mainMenuBar);
		
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(m, BorderLayout.SOUTH);
        add(vp, BorderLayout.CENTER);
		setGlassPane(puw);
        setSize(w, h);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
    	if (menuItem == ae.getSource())
    		System.exit(0);
    }
    
}
    
