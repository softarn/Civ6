package civ;

import java.util.ArrayList;

public class Move {
    private static final State state = State.getInstance();
    private static final GameMap gm = GameMap.getInstance();

    /**
     * Used to move units out of a hold. Does not automatically remove the unit.
     * Expects the unit to be removed from the hold if the function returns true.
     */
    public static boolean makeMove(PhysicalUnit u1, Tile t1, Tile t2){
        int length = moveLength(t1, t2, u1.getCurrentMovementPoint());
        System.out.println("Moving unit");
        if(length == -1){
            return false;
        }
        t2.setUnit(u1);
        for(Tile t : gm.getNeighbours(t2, u1.getType().getVision())){
            t.setExplored(true);
            t.getView().repaint();
        }
        u1.useMovementPoints(length);
        u1.getView().update();
        return true;
    }

    public static boolean makeMove(Tile t1, Tile t2){
        PhysicalUnit unit = t1.getUnit();

        int length = moveLength(t1, t2, unit.getCurrentMovementPoint());

        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(t1);
        tiles.add(t2);
        if(!GameServer.makeMove(tiles)){
            return false;
        }
        if(length == -1){
            return false;
        }
        t1.setUnit(null);
        t2.setUnit(unit);
        for(Tile t : gm.getNeighbours(t2, unit.getType().getVision())){
            t.setExplored(true);
            t.getView().repaint();
        }
        unit.useMovementPoints(length);
        unit.getView().update();
        return true;
    }

    private static int moveLength(Tile t1, Tile t2, int movementPoints){
        for(int i = 1; i <= movementPoints; i++){
            for(Tile t : gm.getNeighbours(t1, i, true)){
                if(t == t2){
                    return i;
                }
            }
        }
        return -1;
    }
}
