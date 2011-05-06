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

public class Window extends JFrame{

	//Menu bar
	private JMenuBar mainMenuBar;
	private JMenu mainMenu;
	private JMenu secondMenu;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private PopupWindow puw;	
	
    //Only for testpurpose
    public static void main(String[] args){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        new Window(size.width,size.height);
    }

    Window(int w,int h){
        super ("Civ 6");
        Round.next(); // Round.next() has to be run atleast once before the player gets to play.
        	
		puw = new PopupWindow();
		
        Menu m = new Menu(puw);
        ViewPort vp = new ViewPort();

		mainMenuBar = new JMenuBar();
		mainMenu = new JMenu("Meny");
		secondMenu = new JMenu ("Help");
		
		menuItem1 = new JMenuItem("Help");
		menuItem2 = new JMenuItem("Avsluta");
		menuItem3 = new JMenuItem("Tutorial");
		
		mainMenu.add(menuItem1);
		mainMenu.add(menuItem2);
		secondMenu.add(menuItem3);
		mainMenuBar.add(mainMenu);
		mainMenuBar.add(secondMenu);
		
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
    
}
    
