package civ;

import java.util.Random;

public class Round{
    private static Player me = null;
    private static int number = 0;
    private static int turn = 0;
    private static Player[] players = {}; 
    private static Player activePlayer = null;
    private static GameMap gm;

    static void next(){
        if(State.isOnline()){
            GameServer.endTurn();
        }
        else{
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
        ++turn;
        System.out.println("Resuming");
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
