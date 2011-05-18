package civ;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.Border;
import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.*;

class GlobalView extends JPanel implements ActionListener{
    
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    
    // Main panels
    private JPanel goldPanel = new JPanel();
    private JPanel sciencePanel = new JPanel();
    private JPanel yearPanel = new JPanel();    
    private JPanel citiesPanel = new JPanel();
    // Specific panels
    private JPanel iconsPanel = new JPanel();
    private JPanel goldLabelPanel = new JPanel();
    
    private JButton scienceButton;
    private JButton endturn;
    private JButton placeHolderButton;
    private JButton showCitiesButton;
    private JProgressBar scienceBar;
    
    //private JLabel goldLabel;
    private JLabel yearLabel;
    private JLabel scienceLabel;
    private JLabel amountCitiesLabel;

    private int scienceValue = 30;    
    
    public GlobalView(){
        super();
        setLayout(new BorderLayout());
        
        scienceBar = new JProgressBar (0,100);
        scienceBar.setSize(new Dimension (50, 15));
        scienceBar.setString("Forskning " + scienceValue+ "%");
        scienceBar.setStringPainted(true);
        scienceBar.setValue(scienceValue);
        
        // Labels
        yearLabel = new JLabel("Year of the beast 666 b.c. ");
        scienceLabel = new JLabel("Forskning: Segling ");
        //goldLabel = new JLabel("<html>Gold: $ 1000 <BR> Inkomst 25 $ / runda <BR> </html>");
        
        // Buttons
        endturn = new JButton("End Turn");
        endturn.setActionCommand("endturn");
        endturn.setPreferredSize(new Dimension (50,30));
        endturn.addActionListener(this);
        
        scienceButton = new JButton("Visa forskning ");
        scienceButton.setPreferredSize(new Dimension(30,25));
        scienceButton.addActionListener(this);
        
        placeHolderButton = new JButton("Visa enheter ");
        placeHolderButton.setPreferredSize(new Dimension(50,25));
        placeHolderButton.addActionListener(this);
        
        showCitiesButton = new JButton ("Visa cities ");
        showCitiesButton.setPreferredSize(new Dimension(50,30));
       
        amountCitiesLabel = new JLabel("Antal cities: 2");
        amountCitiesLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(10,10,10,10)));

        // Add and set layout manager
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        
        // Add panels to *Panel
        eastPanel.add(iconsPanel);
        iconsPanel.setLayout(new BorderLayout());
        iconsPanel.setAlignmentX(eastPanel.LEFT_ALIGNMENT);
        iconsPanel.setAlignmentY(eastPanel.TOP_ALIGNMENT);
        iconsPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(1,1,1,1)));
        
        eastPanel.add(yearPanel); 
        yearPanel.setAlignmentX(eastPanel.LEFT_ALIGNMENT);
        yearPanel.setAlignmentY(eastPanel.BOTTOM_ALIGNMENT);
        yearPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(15,15,15,15)));
        
        westPanel.add(goldPanel); 
        goldPanel.setAlignmentX(westPanel.RIGHT_ALIGNMENT);
        goldPanel.setAlignmentY(westPanel.TOP_ALIGNMENT);
        goldPanel.add(Box.createRigidArea(new Dimension(5,5)));
        goldPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(1,1,1,1)));
        
        westPanel.add(sciencePanel); 
        sciencePanel.setAlignmentX(westPanel.RIGHT_ALIGNMENT);
        sciencePanel.setAlignmentY(westPanel.BOTTOM_ALIGNMENT);
        sciencePanel.add(Box.createRigidArea(new Dimension(5,5)));
        sciencePanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5,5,5,5)));
        
        // Add content to panels 
        goldPanel.add(goldLabelPanel);
        goldPanel.add(citiesPanel);
        
        iconsPanel.add(Box.createRigidArea(new Dimension(0,5)));
        iconsPanel.add(placeHolderButton, BorderLayout.NORTH);
        
        sciencePanel.add(scienceLabel);
        sciencePanel.add(scienceBar);
        sciencePanel.add(scienceButton);
        yearPanel.add(yearLabel);
        yearPanel.add(endturn);
        
        //goldLabelPanel.add(goldLabel);
        //goldLabelPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5,5,5,5)));
        goldLabelPanel.add(Box.createRigidArea(new Dimension(5,5)));
        goldLabelPanel.setAlignmentX(goldPanel.RIGHT_ALIGNMENT);
        goldLabelPanel.setAlignmentY(goldPanel.TOP_ALIGNMENT);
        
        citiesPanel.add(amountCitiesLabel);
        citiesPanel.add(showCitiesButton);
        citiesPanel.setLayout(new BoxLayout(citiesPanel, BoxLayout.Y_AXIS));
        citiesPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5,5,5,5)));
        citiesPanel.setAlignmentX(goldPanel.LEFT_ALIGNMENT);
        citiesPanel.setAlignmentY(goldPanel.TOP_ALIGNMENT);
        
        
        // Gold panel - Top Right Corner 
        goldPanel.setLayout(new BoxLayout(goldPanel, BoxLayout.X_AXIS));
        //goldLabel.setAlignmentX(goldPanel.RIGHT_ALIGNMENT);
        //goldLabel.setAlignmentY(goldPanel.TOP_ALIGNMENT);
        
        // Science panel - Bottom Left Corner
        scienceLabel.setAlignmentX(sciencePanel.LEFT_ALIGNMENT);
        scienceLabel.setAlignmentY(sciencePanel.TOP_ALIGNMENT);
        
        sciencePanel.setLayout(new BoxLayout(sciencePanel, BoxLayout.Y_AXIS));
        scienceBar.setAlignmentX(sciencePanel.LEFT_ALIGNMENT);
        scienceBar.setAlignmentY(sciencePanel.BOTTOM_ALIGNMENT);
        
        scienceButton.setAlignmentX(sciencePanel.LEFT_ALIGNMENT);
        scienceButton.setAlignmentY(sciencePanel.BOTTOM_ALIGNMENT);
        
        
        // Year Panel - Bottom Right Corner
        yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
        //yearLabel.setAlignmentX(yearPanel.RIGHT_ALIGNMENT);
        //yearLabel.setAlignmentY(yearPanel.CENTER_ALIGNMENT);
        
        //endturn.setAlignmentX(yearPanel.RIGHT_ALIGNMENT);        
        //endturn.setAlignmentY(yearPanel.BOTTOM_ALIGNMENT);        
        
    } // public

    public void actionPerformed(ActionEvent ae){
        if(endturn == ae.getSource()){
            Round.next();
        }
        
        else if (placeHolderButton == ae.getSource()){
        	ViewPort.getPopup().setVisible(true);
        }
    } 
    
} // class 
