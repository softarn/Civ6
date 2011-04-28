package civ;

import java.util.Random;
import java.lang.Math;
import static civ.State.UnitState.UnitUnSelected;

public class Battle {

    private static State state = State.getInstance();

	public static int doBattle(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2) {
		
		int winnerId = 0;
		
		// Get variables from object u1 (attacking) and U2 (defending) 
        PhysicalUnitType uta = u1.getType();
        PhysicalUnitType utd = u2.getType();

        TerrainType tt1 = t1.getTerrain();
        TerrainType tt2 = t2.getTerrain();
		
		String name1 = uta.getName();
		String name2 = utd.getName();

		int dp = utd.getDefence();
        if(utd == PhysicalUnitType.Pikeman && uta.isMounted())
            dp = 8;

		int ap = uta.getAttack();
		
		int range1 = uta.getRange();
		int range2 = utd.getRange();
		
		int movementPoint1 = u1.getCurrentMovementPoint();
		int movementPoint2 = u2.getCurrentMovementPoint();
		
		int mpa = u1.getManPower();
		int mpd = u2.getManPower();
		
		int attackBonus = tt1.getAttackBonus();
		int defenceBonus = tt2.getDefenceBonus();
		
		// Attacking object losing manpower randomNumber2 and defending losing randomNumber1 
		Random rand = new Random();
        int charges = rand.nextInt(11) + rand.nextInt(11) + 2;
		
		for (int i = 0; i <= charges; i++) { 
            int audp = (int)Math.round((ap * mpa) / 100.0);
            System.out.println("DP: "+dp+" MDP:"+mpd+" calc:"+ ((dp * mpd) / 100.0) + " round:"+Math.round((dp * mpd) / 100.0));
            int dudp = (int)Math.round((dp * mpd) / 100.0);

            System.out.println("battle a: " + audp);
            System.out.println("battle d: " + dudp);

            int dau = 0;
            int ddu = 0;
            if(audp > 1)
                dau = rand.nextInt(audp) + rand.nextInt(audp);
            if(dudp > 1)
                ddu = rand.nextInt(dudp) + rand.nextInt(dudp);

			mpa -= ddu;
			mpd -= dau;
			
            System.out.println(mpa);
            System.out.println(mpd);

            u1.setManPower(mpa);
            u2.setManPower(mpd);
			
			if (mpa < 1 && mpd < 1){
                state.setUnitState(UnitUnSelected);
                t1.setUnit(null);
                t2.setUnit(null);
				break;
			}else if(mpa < 1) {
                t1.setUnit(null);
                state.setUnitState(UnitUnSelected);
				winnerId = 1;
				break;
			}else if(mpd < 1){
                t2.setUnit(null);
                state.setHoverState(State.HoverState.HoverTileOnly);
				winnerId =- 1;
				break;
			}
			
		}// for
		
		return winnerId;
	}
}
