package src;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static src.State.UnitState.Selected;
import static src.State.UnitState.UnSelected;
import static src.State.ActionState.Move;
import static src.State.ActionState.Attack;

public class Menu extends JPanel implements Observer, ActionListener{

    private static final State state = State.getInstance();

    private JButton move;
    private JButton attack;

    //Status is only for testing purpose
    private JLabel status = new JLabel("Status is: " + state.getUnitState());

    Menu(){
        super();

        move = new JButton("Move");
        //move.setMnemonic(KeyEvent.VK_D);
        move.setActionCommand("move");
        move.addActionListener(this);

        attack = new JButton("Attack");
        attack.setActionCommand("attack");
        attack.addActionListener(this);

        state.addObserver(this);
        add(status);
        add(move);
        add(attack);

        update();
    }

    public void update(Observable obs, Object obj){
        if(obs == state){
            update();
        }
    }

    private void update(){
        switch(state.getUnitState()){
            case UnSelected: 
                move.setEnabled(false); 
                attack.setEnabled(false);
                break;
            case Selected: 
                move.setEnabled(true);
                attack.setEnabled(true);
                switch(state.getActionState()){
                    case Move: move.setEnabled(false); break;
                    case Attack: move.setEnabled(false); break;
                }
                break;
        }
        updateState();
    }

    private void updateState(){
        status.setText("Status is: " + state.getUnitState());
    }

    public void actionPerformed(ActionEvent ae){
        if(move == ae.getSource()){
            state.setActionState(Move);
        }
        if(attack == ae.getSource()){
            state.setActionState(Attack);
        }
    }
}
