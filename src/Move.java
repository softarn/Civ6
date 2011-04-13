package src;

public class Move {
	
	public static boolean makeMove(Tile t1, Tile t2){
        PhysicalUnit unit = t1.getUnit();
        t1.setUnit(null);
        t2.setUnit(unit);
        return true;
	}
	
}
