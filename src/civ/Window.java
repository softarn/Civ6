package civ;

import java.util.Scanner;

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

import proxy.Proxy;
import proxy.Result;
import proxy.FailedException;

public class Window extends JFrame implements ActionListener{

    //Menu bar
    private JMenuBar mainMenuBar;
    private JMenu mainMenu;
    private JMenuItem menuItem;
   // private PopupWindow puw;	

    //Only for testpurpose
    public static void main(String[] args){
    	StartScreen st = new StartScreen();
    }

    Window(int w,int h) {
        super ("Civ 6");
        
        if(!State.isOnline()){
            Round.addPlayer(Player.getInstance("Player"));
            Round.setMe(Player.getInstance("Player"));
        }

        Menu m = new Menu();
        ViewPort vp = new ViewPort();

        mainMenuBar = new JMenuBar();
        mainMenu = new JMenu("Meny");

        menuItem = new JMenuItem("Avsluta");

        mainMenu.add(menuItem);
        mainMenuBar.add(mainMenu);

        menuItem.addActionListener(this);

        setJMenuBar(mainMenuBar);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(m, BorderLayout.SOUTH);
        add(vp, BorderLayout.CENTER);
        setSize(w, h);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (menuItem == ae.getSource())
            System.exit(0);
    }

}

