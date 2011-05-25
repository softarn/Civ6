package civ;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import proxy.Proxy;
import proxy.FailedException;
import proxy.Result;
import proxy.PacketListener;

import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;
import static civ.State.TileState.TileSelected;
import static civ.State.TileState.TileUnSelected;


public class MyPacketListener implements PacketListener{
    GameMap gm = GameMap.getInstance();
    public void newTurn(Result received){
        gm.clearTiles();
        int numberStartingPositions = received.getNumberTiles();
        for(int i=0; i<numberStartingPositions; i++){
            int tileXValue = received.getTileX(i);
            int tileYValue = received.getTileY(i);
            if(received.existUnit(i)){
                String theOwner = received.getUnitOwner(i);
                String theType = received.getUnitType(i);
                int manPowerLeft = received.getUnitManPower(i);
                PhysicalUnit pu = new PhysicalUnit(theType, Player.getInstance(theOwner));
                gm.getTile(tileXValue, tileYValue).setUnit(pu);
                System.out.println(tileXValue +", "+ tileYValue);
                pu.getView().update();
                if(State.getInstance().getUnitState() == UnitSelected){
                    State.getInstance().setUnitState(UnitUnSelected);
                    State.getInstance().setTileState(TileUnSelected);
                    State.getInstance().setUnitState(UnitSelected);
                    State.getInstance().setTileState(TileSelected);
                }
            }
            if(received.existCity(i)){
                String theOwner = received.getCityOwner(i);
                String theName = received.getCityName(i);
                Tile tile = gm.getTile(tileXValue, tileYValue);
                if(tile.hasCity()){
                    tile.getCity().setName(theName);
                    tile.getCity().setOwner(Player.getInstance(theOwner));
                }
                else{
                    tile.setCity(new City(theName, Player.getInstance(theOwner)));
                }

                //List<String> buildings = received.getCityBuildings(i);

                Hold hold = tile.getCity().getHold();
                hold.clear();
                int amountCityUnits = received.getAmountCityUnits(i);
                for(int j=0; j<amountCityUnits; j++){
                    String cityUnitOwner = received.getCityUnitOwner(i, j);
                    String cityUnitType = received.getCityUnitType(i, j);
                    int cityUnitManpower = received.getCityUnitManPower(i, j);
                    PhysicalUnit pu = new PhysicalUnit(cityUnitType, Player.getInstance(cityUnitOwner));
                    pu.setManPower(cityUnitManpower);
                    hold.addUnit(pu);
                    System.out.println("Found a "+pu.getType().getName());
                }
  
            }
            //String improvement = received.getImprovement(i);
        }

        Round.resume();
    }

    public void wasBombarded(Result received){
        System.out.println("We was Bombarded!");
    }

    public void casualtyReport(Result received){
        System.out.println("Report received");
    }

    public void lobbyUpdated(Result received){
        boolean gameLocked = received.getLocked();
        int amount = received.getNumberPlayers();
        for(int i=0; i<amount; i++){
            String playerName = received.getPlayerName(i);
            String playerCivilization = received.getPlayerCiv(i);
            Round.addPlayer(Player.getInstance(playerName));
            GameScreen.getInstance().update();
        }
    }

    public void gameStarted(Result received){
        System.out.println("Starting game");
        GameMap gm = GameMap.getInstance();
        gm.parseMap((ArrayList<ArrayList<String>>)received.getMap());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = (JFrame)SwingUtilities.getRoot(GameScreen.getInstance());
        frame.dispose();
        new Window(size.width,size.height);
    }

    public void chatMessageReceived(Result received){
    }

    public void gameClosed(){
        System.out.println("The game was closed");
    }
}
