package civ;

import java.util.Random;
import java.lang.Math;
import static civ.State.UnitState.UnitUnSelected;

public class Battle {

    private static State state = State.getInstance();

	public static int doBattle(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2) {
		
		int winnerId=0;
		
		// Get variables from object u1 (attacking) and U2 (defending) 
        PhysicalUnitType ut1 = u1.getType();
        PhysicalUnitType ut2 = u2.getType();

        TerrainType tt1 = t1.getTerrain();
        TerrainType tt2 = t2.getTerrain();
		
		String name1 = ut1.getName();
		String name2 = ut2.getName();

		int dp = ut2.getDefence();

		int ap = ut1.getAttack();
		
		int range1 = ut1.getRange();
		int range2 = ut2.getRange();
		
		int movementPoint1 = u1.getCurrentMovementPoint();
		int movementPoint2 = u2.getCurrentMovementPoint();
		
		int mp1 = u1.getManPower();
		int mp2 = u2.getManPower();
		
		int attackBonus = tt1.getAttackBonus();
		int defenceBonus = tt2.getDefenceBonus();
		
		// Attacking object losing manpower randomNumber2 and defending losing randomNumber1 
		Random rand = new Random();
        int charges = rand.nextInt(11) + rand.nextInt(11) + 2;
		
		for (int i = 0; i <= charges; i++) { 
            int audp = round((ap * mp1) / 100);
            int dudp = round((dp * mp2) / 100);

            int dau = rand.nextInt(audp) + rand.nextInt(audp);
            int ddu = rand.nextInt(dudp) + rand.nextInt(dudp);

			mp1 -= ddu;
			mp2 -= dau;
			
            System.out.println(mp1);
            System.out.println(mp2);

            u1.setManPower(mp1);
            u2.setManPower(mp2);
			
			if (mp1<1 && mp2<1){
                state.setUnitState(UnitUnSelected);
                t1.setUnit(null);
                t2.setUnit(null);
				break;
			}else if(mp1<=0) {
                t1.setUnit(null);
                state.setUnitState(UnitUnSelected);
				winnerId=1;
				break;
			}else if(mp2<=0){
                t2.setUnit(null);
				winnerId=-1;
				break;
			}
			
		}// for
		
		return winnerId;
	}

    private static int round(double d) {
        return (int)Math.floor(d + 0.5f);
    }
}
