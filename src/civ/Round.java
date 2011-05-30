package civ;

import java.util.Random;
import java.awt.Component;
import javax.swing.JOptionPane;

import static civ.State.CityState.CitySelected;
import static civ.State.CityState.CityUnSelected;
import static civ.State.TileState.TileSelected;
import static civ.State.TileState.TileUnSelected;
import static civ.State.UnitState.UnitSelected;
import static civ.State.UnitState.UnitUnSelected;

public class Round{
    private static final State state = State.getInstance();
    private static Player me = null;
    private static int number = 0;
    private static int turn = 0;
    private static Player[] players = {}; 
    private static City[] cities = {};
    private static Player activePlayer = null;
    private static GameMap gm = GameMap.getInstance();

    static void next(){
        countAll();
        GameServer.endTurn(); 
        if(!State.isOnline()){
            resume();
            System.out.println("You are waiting for your turn");
        }
        else {
          JOptionPane.showMessageDialog(null,"Du väntar på din tur", "Spelmeddelande",
            JOptionPane.INFORMATION_MESSAGE);	
        }
    }

    static void resume(){
        System.out.println("I has new turn!");
        
        if(State.isOnline()){
            JOptionPane.showMessageDialog(null,"Nu är det din tur ", "Spelmeddelande",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        gm.exploreMap();
        if(gm.isInited()){
            gm.resetUnits();
            if(!state.isOnline()){
                spawnBarbarian();
            }
        }
        ++turn;
        Component[] comps = Menu.getInstance().getComponents();
        for(Component c : comps){
            if(c instanceof GlobalView){
                ((GlobalView)c).update();
            }
        }
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

    public static void addCity(City city){
        City[] temp = new City[cities.length+1];
        int i=0;
        for(City tempCity : cities){
            if(tempCity.equals(city)){
                // No copies allowed
                return;
            }
            temp[i++] = tempCity;
        }
        temp[i] = city;
        cities = temp;
    }

    public static void delCity(City city){
        City[] temp = new City[players.length-1];
        int i=0;
        for(City tempCity : cities){
            if(!tempCity.equals(city)){
                temp[i++] = tempCity;
            }
        }
    }

    private static void countAll(){
        for(City city : cities){
           city.count(); 
        }
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
                    System.out.println("Spawning a barbarian of type: " + t.getUnit().getType().getCategory());
                    return;
                }
            }
        }
    }

    public static int getTurn(){
        return turn;
    }

    public static Player[] getPlayers(){
        return players;
    }

    public static Player getActivePlayer(){
        return activePlayer;
    }
    
    public static void gameOver(){
    		
    }
}
