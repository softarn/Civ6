package civ;

import java.awt.Color;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import java.util.Observer;
import java.util.Observable;

import static civ.State.ActionState.Move;
import static civ.State.ActionState.Attack;

import static civ.State.UnitState.UnitSelected;

class CityView extends JPanel {
	private JLabel name = new JLabel();
    private JLabel image = new JLabel(); 
   
    private JPanel imgPane = new JPanel();
    
    public CityView() {
    /*	imgPane.add(image);
        imgPane.setMaximumSize(new Dimension(pUnit.getImage().getWidth() + 20, 
        		pUnit.getImage().getHeight() + 20));
		*/		    	

    }


}















