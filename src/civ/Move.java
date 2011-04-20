package civ;

public class Move {
    private static final GameMap gm = GameMap.getInstance();
	
	public static boolean makeMove(Tile t1, Tile t2){
        PhysicalUnit unit = t1.getUnit();
        int length = moveLength(t1, t2, unit.getCurrentMovementPoint());

        if(length == -1)
            return false;

        t1.setUnit(null);
        t2.setUnit(unit);
        for(Tile t : gm.getNeighbours(t2, t2.getUnit().getType().getVision())){
            t.setExplored(true);
            t.getView().repaint();
        }
        unit.useMovementPoints(length);
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
