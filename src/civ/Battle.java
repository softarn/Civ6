package civ;

import java.util.Random;
import java.lang.Math;
import static civ.State.UnitState.UnitUnSelected;

public class Battle {

    private static State state = State.getInstance();
    private static GameMap gm = GameMap.getInstance();
    private static int attackerLoss;
    private static int defenderLoss;

    private static PhysicalUnitType uta;
    private static PhysicalUnitType utd;
    private static TerrainType tt1;
    private static TerrainType tt2;
    private static String name1;
    private static String name2;
    private static int ap; // Attack points
    private static int dp; // Defence points
    private static int tab; // Terrain Attack Points
    private static int tdb; // Terrain Defence Points
    private static int amp; // Current Attacking Manpower
    private static int dmp; // Current Defending Manpower
    private static int mamp; // Max Attacking Manpower
    private static int mdmp; // Max Defending Manpower
    private static int range1; // Attacker range
    private static int range2; // Defender range
    private static int movementPoint1; // Attacker movementpoints
    private static int movementPoint2; // Defender movementpoints
    private static Random rand = new Random();

    private static void fetchStats(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2){

		// Get variables from object u1 (attacking) and u2 (defending) 
        uta = u1.getType();
        utd = u2.getType();

        tt1 = t1.getTerrain();
        tt2 = t2.getTerrain();

        tab = tt1.getAttackBonus();
        tdb = tt2.getDefenceBonus();
		
		name1 = uta.getName();
		name2 = utd.getName();

		dp = utd.getDefence();
        if(utd == PhysicalUnitType.Pikeman && uta.isMounted())
            dp = 12;
        if(u2.isFortified()){
            dp = round(dp * 1.5);
        }
        dp = round(dp * (1 + (tdb/100.0)));

		ap = uta.getAttack();
		if(u1.inSiegeTower()){
            ap = ap * 2;
        }
        ap = round(ap * (1 + (tab/100.0)));

		range1 = uta.getRange();
		range2 = utd.getRange();
		
		movementPoint1 = u1.getCurrentMovementPoint();
		movementPoint2 = u2.getCurrentMovementPoint();
		
		amp = mamp = u1.getManPower();
		dmp = mdmp = u2.getManPower();
    }

    private static int ranged(){
		int winnerId = 0;
        int wave = rand.nextInt(3) + rand.nextInt(3) + 2;
        
		for (int i = 0; i < wave; i++) { 
            int audp = round((ap * amp) / 100.0);
            int dau = rand.nextInt(audp) + rand.nextInt(audp);
            dmp -= dau;
			if (amp < 1 && dmp < 1){
                winnerId = 2;
				break;
			}else if(amp < 1) {
				winnerId = 1;
				break;
			}else if(dmp < 1){
				winnerId = -1;
				break;
			}
        }
        return winnerId;
    }

    private static int bombardment(){
		int winnerId = 0;
        int audp = round((ap * amp) / 100.0);
        int dau = rand.nextInt(audp) + rand.nextInt(audp);
        dmp -= dau;
        if (amp < 1 && dmp < 1){
            winnerId = 2;
        }else if(amp < 1) {
            winnerId = 1;
        }else if(dmp < 1){
            winnerId = -1;
        }
        return winnerId;
    }

    private static int normal(){
        int winnerId = 0;
        int charges = rand.nextInt(12) + rand.nextInt(12) + 2;

        for (int i = 0; i < charges; i++) { 
            int audp = round((ap * amp) / 100.0);
            int dudp = round((dp * dmp) / 100.0);

            System.out.println("DP: "+dp+" MDP:"+dmp+" calc:"+ ((dp * dmp) / 100.0) + " round:"+round((dp * dmp) / 100.0));
            System.out.println("battle a: " + audp);
            System.out.println("battle d: " + dudp);

            int dau = rand.nextInt(audp) + rand.nextInt(audp);
            int ddu = rand.nextInt(dudp) + rand.nextInt(dudp);

			amp -= ddu;
            dmp -= dau;
			
            System.out.println(amp);
            System.out.println(dmp);

			if (amp < 1 && dmp < 1){
                winnerId = 2;
				break;
			}else if(amp < 1) {
				winnerId = 1;
				break;
			}else if(dmp < 1){
				winnerId = -1;
				break;
			}
			
		}
        return winnerId;
    }

    public static int doAverageBattle(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2) {
		int winnerId = 0;
		fetchStats(u1, u2, t1, t2);
        if(1 == attackRange(t1, t2, range1)){
            return 0;
        }
		
        if(u1.getType().getCategory().equals("Ranged") ||
                u1.getType().getName().equals("Trireme")){
            winnerId = ranged();
        }
        else if(u1.getType().getCategory().equals("Artillery") ||
                u1.getType().getName().equals("Galley") ||
                u1.getType().getName().equals("Caravel")){
            winnerId = bombardment();
        }
        else{
            winnerId = normal();
        }

        u1.setManPower(amp);
        u2.setManPower(dmp);
			
		return winnerId;
    }

	public static int doBattle(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2) {
		
		int winnerId = 0;
		fetchStats(u1, u2, t1, t2);
        if(1 == attackRange(t1, t2, range1)){
            return 0;
        }

        // Remove the movement cost 1 from the attacking unit
        if(!u1.useMovementPoints(1)){
            return 0;
        }

        if(u1.getType().getCategory().equals("Ranged") ||
                u1.getType().getName().equals("Trireme")){
            winnerId = ranged();
        }
        else if(u1.getType().getCategory().equals("Artillery") ||
                u1.getType().getName().equals("Galley") ||
                u1.getType().getName().equals("Caravel")){
            winnerId = bombardment();
        }
        else{
            winnerId = normal();
        }
        u1.setManPower(amp);
        u2.setManPower(dmp);
			
	    attackerLoss = mamp-u1.getManPower();
        defenderLoss = mdmp-u2.getManPower();

        if(winnerId == 2){
            state.setUnitState(UnitUnSelected);
            state.setHoverState(State.HoverState.HoverTileOnly);
            t1.setUnit(null);
            t2.setUnit(null);
        }
        if(winnerId == 1){
            state.setUnitState(UnitUnSelected);
            t1.setUnit(null);
        }
        if(winnerId == -1){
            state.setHoverState(State.HoverState.HoverTileOnly);
            t2.setUnit(null);
        }
		return winnerId;
	}

    public static int getAttackerLoss(){
        return attackerLoss;
    }

    public static int getDefenderLoss(){
        return defenderLoss;
    }

    private static int round(double d) {
        return (int)Math.floor(d + 0.5f);
    }

    public static int attackRange(Tile t1, Tile t2, int range){
            for(Tile t : gm.getNeighbours(t1, range, false)){
                if(t == t2){
                    return 1;
                }
            }
        return -1;
    }

}
