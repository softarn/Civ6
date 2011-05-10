package civ;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;

import proxy.Result;
import proxy.PacketListener;

public class MyPacketListener implements PacketListener{
	public void newTurn(Result received){
        System.out.println("I has new turn!");
    }

	public void lobbyUpdated(Result received){
    }

	public void gameStarted(Result received){
        GameMap gm = GameMap.getInstance();
        gm.parseMap((ArrayList<ArrayList<String>>)received.getMap());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        new Window(size.width,size.height);
    }

	public void chatMessageReceived(Result received){
    }

    public void gameClosed(){
    }
}
