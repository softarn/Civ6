package civ;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

/**
 * PhysicalUnitView is the GUI representation of the information in the bottom
 * bar.
 */
public class PhysicalUnitView extends JPanel{
    private PhysicalUnit pUnit;
    private JLabel name = new JLabel();
    private JLabel image = new JLabel();
    private JPanel imgPane = new JPanel(); 
    private JPanel infoPane = new JPanel();
    private JPanel buttonPane = new JPanel(); 

    private JProgressBar manPower;    
    private JLabel defence = new JLabel();
    private JLabel range = new JLabel();
    private JLabel attack = new JLabel();
    private JLabel movement = new JLabel();
    private JLabel foodstorage = new JLabel();

    public PhysicalUnitView(PhysicalUnit pu){
        pUnit = pu;
        setLayout(new BorderLayout());

        imgPane.add(image);
        imgPane.setPreferredSize(new Dimension(pUnit.getImage().getWidth()+20, pUnit.getImage().getHeight() + 20));
        
        buttonPane = createButtonPane();
        infoPane = createInfoPane();

        add(name, BorderLayout.NORTH);
        add(imgPane, BorderLayout.WEST);
        add(buttonPane, BorderLayout.CENTER);
        add(infoPane, BorderLayout.EAST);

        update();
    }

    private JPanel createInfoPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel upper = new JPanel();
        JPanel lower = new JPanel();
        
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        upper.setLayout(gridbag);
        upper.setPreferredSize(new Dimension(300, 115));
        upper.setBorder(BorderFactory.createTitledBorder("Strid:"));

        manPower = new JProgressBar(0, pUnit.getType().getMaxManPower());  

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        upper.add(manPower, c);

        c.gridy = 1;
        upper.add(attack, c);

        c.gridx = 1;
        c.gridy = 0;
        upper.add(range, c);
        
        c.gridy = 1;
        upper.add(defence, c);

        lower.add(movement);
        lower.add(foodstorage);
        lower.setBorder(BorderFactory.createTitledBorder(""));

        panel.add(upper);
        panel.add(lower);
        return panel;
    }

    private JPanel createButtonPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JButton("Attackera"));
        panel.add(Box.createRigidArea(new Dimension(0,7)));
        panel.add(new JButton("Befästa"));
        panel.add(Box.createRigidArea(new Dimension(0,7)));
        panel.add(new JButton("Förflytta"));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        return panel;
    }

    public void update(){
        name.setText(pUnit.getType().getName());
        if(pUnit.isAlly()){
            updateAlly();
        }
        else{
            updateEnemy();
        }
    }

    private void updateEnemy(){
        imgPane.setBorder(BorderFactory.createMatteBorder(6,6,6,6,Color.RED));
        image.setIcon(new ImageIcon(pUnit.getType().getImage()));
    }

    private void updateAlly(){
        imgPane.setBorder(BorderFactory.createMatteBorder(6,6,6,6,Color.GREEN));
        image.setIcon(new ImageIcon(pUnit.getType().getImage()));
        manPower.setValue(pUnit.getManPower());
        manPower.setString("Mankraft: " + pUnit.getManPower()); 
        manPower.setStringPainted(true);
        defence.setText("Försvarsvärde: "+ pUnit.getType().getDefence());
        range.setText("Räckvidd: "+ pUnit.getType().getRange());
        attack.setText("Attackvärde: "+ pUnit.getType().getAttack());
        movement.setText("Förflyttning: " + pUnit.getCurrentMovementPoint() + 
                "/" + pUnit.getType().getMovementPoints());
        foodstorage.setText("Matlager: X/X");
    }

    private void updateSettler(){
        // To be written at a later date
    }
}
