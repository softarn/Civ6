package civ;

import java.util.Random;
import javax.swing.JOptionPane;

public class Round{
    private static Player me = null;
    private static int number = 0;
    private static int turn = 0;
    private static PhysicalUnitType ptype;
    private static Tile ptile;
    private static int pcost = 0;
    private static Player[] players = {}; 
    private static Player activePlayer = null;
    private static GameMap gm;

    static void next(){
        GameServer.endTurn();
        if(!State.isOnline()){
            resume();
        }
    }

    static void resume(){
        System.out.println("I has new turn!");
        gm = GameMap.getInstance();
        gm.exploreMap();
        if(gm.isInited()){
            gm.resetUnits();
            spawnBarbarian();
        }
        count();
        ++turn;
        System.out.println("Resuming");
    }

    private static void count(){
        if(pcost == 1){
            //spawn unit here
            System.out.println("Spawning unit");
            ptile.getCity().getHold().addUnit(new PhysicalUnit(ptype, getMe()));
            ptile.getView().repaint();
            GameServer.makeUnit(ptile, ptype);
            int choice = JOptionPane.showConfirmDialog( null, "En ny enhet, " + ptype.getName() + ", har blivit skapad. Vill du gå till dess position?", "Enhet skapad", JOptionPane.YES_NO_OPTION);
        }
        if(pcost >= 0){
            --pcost;
        }
    }

    public static void spawnCounter(PhysicalUnitType type, Tile tile, int cost){
        ptype = type;
        ptile = tile;
        pcost = cost;
    }

    /**
     * Sets which player who is playing at this instance of the game.
     */
    public static void setMe(Player player){
        me = player;
    }

    public static void addPlayer(Player player){
        Player[] temp = new Player[players.length+1];
        int i=0;
        for(Player tempPlayer : players){
            if(tempPlayer.equals(player)){
                // No copies allowed
                return;
            }
            temp[i++] = tempPlayer;
        }
        temp[i] = player;
        players = temp;
    }

    public static void delPlayer(Player player){
        Player[] temp = new Player[players.length-1];
        int i=0;
        for(Player tempPlayer : players){
            if(!tempPlayer.equals(player)){
                temp[i++] = tempPlayer;
            }
        }
    }

    public static Player getMe(){
        return me;
    }

    private static void spawnBarbarian(){
        Random r = new Random();
        int x;
        int y;
        int count = 0;
        if(r.nextInt(100) < 20){
            while(++count < 100){
                x = r.nextInt(gm.getWidth());
                y = r.nextInt(gm.getHeight());
                Tile t = gm.getTile(x, y);
                if(t != null &&
                        !t.getTerrain().getName().equals("Sea") &&
                        !t.getTerrain().getName().equals("Ocean") &&
                        !t.isExplored() &&
                        !t.hasUnit()){
                    t.setUnit(new Barbarian(t));
                    return;
                }
            }
        }
    }

    public static Player[] getPlayers(){
        return players;
    }

    public static Player getActivePlayer(){
        return activePlayer;
    }
}
