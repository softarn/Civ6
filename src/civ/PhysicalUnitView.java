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

/**
 * PhysicalUnitView is the GUI representation of the information in the bottom
 * bar.
 */
public class PhysicalUnitView extends JPanel implements Observer, ActionListener{
    private static final State state = State.getInstance();
    private PhysicalUnit pUnit;
    private JLabel name = new JLabel();
    private JLabel image = new JLabel();
    private JPanel imgPane = new JPanel(); 
    private JPanel rightPane = new JPanel();
    private JPanel leftPane = new JPanel();
    private JPanel upperPane;
    private JPanel lowerPane;

    private JButton atkButton = new JButton("Attackera");
    private JButton defButton = new JButton("Befästa");
    private JButton moveButton = new JButton("Förflytta");
    private JButton infoButton = new JButton("Visa mer info");

    private JProgressBar manPower;    
    private JProgressBar lifeLength;
    private JLabel defence = new JLabel();
    private JLabel range = new JLabel();
    private JLabel attack = new JLabel();
    private JLabel movement = new JLabel();
    
    private Popup popup;

    public PhysicalUnitView(PhysicalUnit pu){
        pUnit = pu;
        JPanel namePane = new JPanel();
        name.setFont(name.getFont().deriveFont(18.0f));
        namePane.add(name);
        manPower = new JProgressBar(0, pUnit.getType().getMaxManPower());
        lifeLength = new JProgressBar();
        setLayout(new BorderLayout());

        atkButton.addActionListener(this);
        moveButton.addActionListener(this);
        defButton.addActionListener(this);
        infoButton.addActionListener(this);git 

        imgPane.add(image);
        imgPane.setMaximumSize(new Dimension(pUnit.getImage().getWidth() + 20, 
                    pUnit.getImage().getHeight() + 20));
        
        upperPane = createUpperPane();
        lowerPane = createLowerPane();

        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
        leftPane.add(namePane);
        leftPane.add(imgPane);
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.PAGE_AXIS));
        rightPane.add(upperPane);
        rightPane.add(lowerPane);

        add(leftPane, BorderLayout.WEST);
        add(rightPane, BorderLayout.CENTER);

        state.addObserver(this);
        update();
    }

    private JPanel createUpperPane(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Strid:"));
        panel.setLayout(new BorderLayout());

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(135, 30));
        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
        left.add(manPower);
        left.add(Box.createRigidArea(new Dimension(0,3)));
        left.add(attack);

        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.PAGE_AXIS));
        middle.setBorder(BorderFactory.createEmptyBorder(10,15,0,0));
        middle.add(range);
        middle.add(Box.createRigidArea(new Dimension(0,8)));
        middle.add(defence);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        right.add(atkButton);
        right.add(Box.createRigidArea(new Dimension(0,5)));
        right.add(defButton);

        panel.add(left, BorderLayout.WEST);
        panel.add(middle, BorderLayout.CENTER);
        panel.add(right, BorderLayout.EAST);

        return panel;
    }

    private JPanel createLowerPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.PAGE_AXIS));
        stats.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        stats.add(movement);
        stats.add(Box.createRigidArea(new Dimension(0,7)));
        stats.add(lifeLength);

        JPanel left = new JPanel();
        left.setBorder(BorderFactory.createTitledBorder(""));
        left.add(stats);
        left.add(moveButton);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        right.setBorder(BorderFactory.createEmptyBorder(25,10,10,10));
        right.add(infoButton);

        panel.add(left, BorderLayout.WEST);
        panel.add(right, BorderLayout.CENTER);
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
        imgPane.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0,0,10,0), 
                    BorderFactory.createMatteBorder(6,6,6,6,Color.GREEN)));
        image.setIcon(new ImageIcon(pUnit.getType().getImage()));
        manPower.setValue(pUnit.getManPower());
        manPower.setString("Mankraft: " + 
                (int)((double)pUnit.getManPower() / pUnit.getType().getMaxManPower() * 100) +
                "%");
        manPower.setStringPainted(true);
        defence.setText("Försvarsvärde: " + pUnit.getType().getDefence());
        range.setText("Räckvidd: " + pUnit.getType().getRange());
        attack.setText("Attackvärde: " + pUnit.getType().getAttack());
        movement.setText("Förflyttning: " + pUnit.getCurrentMovementPoint() + 
                "/" + pUnit.getType().getMovementPoints());
        
        lifeLength.setMaximum(pUnit.getType().getInventorySize());
        lifeLength.setString("Livslängd: "+ Integer.toString(pUnit.getInventoryAmount()));
        lifeLength.setValue(pUnit.getInventoryAmount());
        lifeLength.setStringPainted(true);
        //lifeLength.paintString(true);

        //inventory.setText("Matlager: " + pUnit.getInventoryAmount() + 
          //      "/" + pUnit.getType().getInventorySize());
    }

    private void updateSettler(){
        // To be written at a later date
    }
    
    public void setPopup (Popup pop) {
    	this.popup = pop;
    }

    public void update(Observable obs, Object obj){
        if(obs == state){
            moveButton.setEnabled(true);
            atkButton.setEnabled(true);

            if(state.getUnitState() == UnitSelected){
                if(state.getSelectedUnit().isAlly()){
                    if(state.getSelectedUnit().getCurrentMovementPoint() == 0){
                        moveButton.setEnabled(false);
                        atkButton.setEnabled(false);
                    }
                    switch(state.getActionState()){
                        case Move:
                            moveButton.setEnabled(false);
                            break;
                        case Attack:
                            atkButton.setEnabled(false);
                            break;
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(moveButton == ae.getSource()){
            if(state.getUnitState() == UnitSelected){
                state.getSelectedUnit().getView().update();
                state.setActionState(Move);
                System.out.println("Move darnit!");
            }
        }
        if(atkButton == ae.getSource()){
            if(state.getUnitState() == UnitSelected){
                state.getSelectedUnit().getView().update();
                state.setActionState(Attack);
            }
        }
        
        if(infoButton == ae.getSource()){
        	if(popup != null){
        		popup.show();
        	}
        }
    }
}
