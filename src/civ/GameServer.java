package civ;

import java.util.List;
import java.util.ArrayList;

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
        if(State.isOnline()){
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
    }

    public static void endTurn(){
        if(State.isOnline()){
            try{
                proxy.endTurn();
            }
            catch(FailedException fe){
                System.out.println("Couldn't end turn");
            }
        }
    }

    public static boolean buildCity(Tile tile, String name){
        if(State.isOnline()){
            int x = tile.getX(), y = tile.getY();
            try{
                proxy.builtCity(x, y, name);
            }
            catch(FailedException fe){
                System.out.println("Couldn't place city");
                return false;
            }
        }
        return true;     
    }

    public static boolean makeUnit(Tile tile, AbstractUnitType type){
        if(State.isOnline()){
            int x = tile.getX(), y = tile.getY();
            try{
                System.out.println("Spawn damnit!");
                System.out.println(x + ":" + y + " = Player: " + Round.getMe() + ", Type: " + type.getName() + ", Manpower: " + type.getMaxManPower());
                proxy.madeUnit(x, y, Round.getMe().toString(), type.getName(), type.getMaxManPower());
                System.out.println("Done!");
            }
            catch(FailedException fe){
                System.out.println("Couldn't place unit");
                return false;
            }
        }
        return true;     
    }

    public static boolean makeMoveOut(PhysicalUnit unit, Tile t1, Tile t2){
        if(State.isOnline()){
            Result r = new Result();
            try{
                proxy.moveOutUnit(t1.getX(), t1.getY(), unit.getType().getName(), 
                        unit.getManPower(), t2.getX(), t2.getY());
            }
            catch(FailedException fe){
                System.out.println("Couldn't move out there");
            }
            return r.getOk();
        }
        return true;
    }

    public static boolean makeMove(ArrayList<Tile> tiles){
        if(State.isOnline()){
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
        return true;
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
            System.out.println("Hosting game");
            returned = proxy.startGame();
            System.out.println(returned.getOk());
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
