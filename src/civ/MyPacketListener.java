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
class GameServer{
    private static Proxy proxy = null;
    public static void init(Proxy p){
        if(proxy == null && p != null) {
            proxy = p;
        }
    }

    public static String[] listGames(){
        Result r = null;
        try{
            r = proxy.listGames();
        }
        catch(FailedException fe){
            System.out.println("Couldn't list games");
        }
        List<String> list = r.getSessions();
        String[] games = new String[list.size()];
        int i = 0;
        for(String temp : list){
            games[i++] = temp;
        }
        return games;
    }

    public static void battle(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2){
        Result result = null;
        try{
            result = proxy.combatRequest(t1.getX(), t1.getY(), t2.getX(), t2.getY());
        }
        catch(FailedException fe){
            System.out.println("Couldn't end turn");
        }
        u1.setManPower(result.getAttackerLeft());
        u2.setManPower(result.getDefenderLeft());

    }

    public static void endTurn(){
        try{
            proxy.endTurn();
        }
        catch(FailedException fe){
            System.out.println("Couldn't end turn");
        }
    }

    public static boolean makeUnit(int x, int y, AbstractUnitType type){
        try{
            proxy.madeUnit(x, y, Round.getMe().toString(), type.getName(), type.getMaxManPower());
        }
        catch(FailedException fe){
            System.out.println("Couldn't place unit");
            return false;
        }
        return true;     
    }

    public static boolean makeMove(ArrayList<Tile> tiles){
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for(Tile t : tiles){
            moves.add(t.getX());
            moves.add(t.getY());
        }
        Result r = new Result();
        try{
            r = proxy.moveUnit(moves);
        }
        catch(FailedException fe){
            System.out.println("Couldn't move");
        }
        return r.getOk();
    }

    public static boolean host(){
        Result returned = null;
        try{
            returned = proxy.host();
            System.out.println(returned.getHostName());
        }
        catch(FailedException fe){
            System.out.println(fe);
            return false;
        }
        return true;
    }

    public static boolean join(String name){
        try{
            proxy.joinGame(name);
        }
        catch(FailedException fe){
            System.out.println(fe);
            return false;
        }
        return true;
    }

    public static boolean startGame(){
        Result returned = null;
        try{
            returned = proxy.startGame();
            return returned.getOk();
        }
        catch(FailedException fe){
            System.out.println(fe);
            return false;
        }
    }

    public static void lockGame(boolean lock){
        try{
            proxy.lockGame(lock);
        }
        catch(FailedException fe){
            System.out.println(fe);
            return;
        }
        
    }
}

public class MyPacketListener implements PacketListener{
    public void newTurn(Result received){
        GameMap gm = GameMap.getInstance();
        //gm.parseMap((ArrayList<ArrayList<String>>)received.getMap());
        gm.clearTiles();
        int numberStartingPositions = received.getNumberTiles();
        for(int i=0; i<numberStartingPositions; i++){
            int tileXValue = received.getTileX(i);
            int tileYValue = received.getTileY(i);
            if(received.existUnit(i)){
                String theOwner = received.getUnitOwner(i);
                String theType = received.getUnitType(i);
                int manPowerLeft = received.getUnitManPower(i);
                gm.getTile(tileXValue, tileYValue).setUnit(new PhysicalUnit(theType, Player.getInstance(theOwner)));
                System.out.println(tileXValue +", "+ tileYValue);
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

        Round.resume();
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
        GameMap gm = GameMap.getInstance();
        gm.parseMap((ArrayList<ArrayList<String>>)received.getMap());
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Starting game");
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
