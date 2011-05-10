package civ;

public class Round{
    private static int number = 0;
    private static int turn = 0;
    private static Player[] players = {Player.getInstance("Andy"), Player.getInstance("Harry")};
    private static Player activePlayer = null;
    private static GameMap gm;
    
    static void next(){
        if(number%players.length == 0){
            ++turn;
        }
        activePlayer = players[number%players.length];
        gm = GameMap.getInstance();
        if(gm.isInited()){
            gm.resetUnits();
        }
        ++number;
    }

    public static void addPlayer(Player player){
        Player[] temp = new Player[players.length+1];
        int i=0;
        for(Player tempPlayer : players){
            temp[i++] = tempPlayer;
        }
        temp[i] = player;
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

    public static Player[] getPlayers(){
        return players;
    }

    public static Player getActivePlayer(){
        return activePlayer;
    }
}
