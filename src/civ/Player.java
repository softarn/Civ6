package civ;

import java.util.HashMap;

public class Player implements Comparable<Player>{
    private String name;
    private static HashMap<String, Player> saved = new HashMap<String, Player>();
    //private Civilisation civ;
    
    private Player(String name){
        this.name = name;
    }

    public static Player getInstance(String name){
        if(saved.containsKey(name)){
            return saved.get(name);
        }
        else{
            return new Player(name);
        }
    }

    public boolean isActive(){
        return Round.getActivePlayer().equals(this);
    }

    /**
     * This function is supposed to return true when the player is the one on
     * the local computer. I don't really know where to put the representation
     * of that right now, so it just returns true for the current player whose 
     * turn it is.
     */
    public boolean isMe(){
        return isActive();
    }

    public String toString(){
        return name;
    }

    public int compareTo(Player other){
        return this.name.compareTo(other.name);
    }

    public boolean equals(Player other){
        return this.name.equals(other.name);
    }
}
