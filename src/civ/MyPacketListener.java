package civ;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;

import proxy.Result;
import proxy.PacketListener;

public class MyPacketListener implements PacketListener{
	public void newTurn(Result received){
        GameMap gm = GameMap.getInstance();
        //gm.parseMap((ArrayList<ArrayList<String>>)received.getMap());
        int numberStartingPositions = received.getNumberTiles();
        for(int i=0; i<numberStartingPositions; i++){
            int tileXValue = received.getTileX(i);
            int tileYValue = received.getTileY(i);
            if(received.existUnit(i)){
                String theOwner = received.getUnitOwner(i);
                String theType = received.getUnitType(i);
                int manPowerLeft = received.getUnitManPower(i);
                gm.getTile(tileXValue, tileYValue).setUnit(new PhysicalUnit(theType, Player.getInstance(theOwner)));
                gm.exploreMap();
            }
            if(received.existCity(i)){
                String theOwner = received.getCityOwner(i);
                String theName = received.getCityName(i);
                gm.getTile(tileXValue, tileYValue).setCity(new City(theName, Player.getInstance(theOwner)));

                /*List<String> buildings = received.getCityBuildings(i);
                int amountCityUnits = received.getAmountCityUnits(i);
                for(int j=0; j<amountCityUnits; j++){
                    String cityUnitOwner = received.getCityUnitOwner(i, j);
                    String cityUnitType = received.getCityUnitType(i, j);
                    int cityUnitManpower = received.getCityUnitManPower(i, j);
                    gm.getTile(tileXValue, tileYValue).setCity();
                }*/
            }
            //String improvement = received.getImprovement(i);
        }

        //Round.resume();
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
        System.out.println("The game was closed");
    }
}
